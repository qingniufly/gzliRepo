package com.simon.elastic.index;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.joda.time.DateTime;
import org.elasticsearch.common.joda.time.format.DateTimeFormat;
import org.elasticsearch.common.joda.time.format.ISODateTimeFormat;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IndexOpTest {
	
	private TransportClient client;
	
	private static final String IDX_NAME = "zen";
	
	private static final String TYPE_NAME = "staff";
	
	private Node node;
	
	@SuppressWarnings("unused")
	private Client nodeClient;
	
	@Before
	public void setUp() {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		client = new TransportClient(settings);
//		client = new TransportClient();
		client.addTransportAddress(new InetSocketTransportAddress("10.3.41.92", 9300));
		
		node = NodeBuilder.nodeBuilder().clusterName("elasticsearch").node();
		nodeClient = node.client();
		
	}
	
	/**
	 * Create Index and set settings and mappings
	 */
	@Test
	public void testCreateIndex() {
		CreateIndexRequestBuilder reqb = client.admin().indices().prepareCreate(IDX_NAME);
		reqb.execute().actionGet();
	}
	
	@Test
	public void testAddDocument() throws IOException {
		IndexRequestBuilder idxb = client.prepareIndex(IDX_NAME, TYPE_NAME, "000");
		XContentBuilder contentBuilder = jsonBuilder().startObject().prettyPrint();
		contentBuilder.field("name", "李承文");
		contentBuilder.field("joindate", DateTime.now());
		contentBuilder.field("dept", "IT Dev");
		contentBuilder.field("title", "Senior SE");
		contentBuilder.field("age", 30);
		contentBuilder.field("birthdate", DateTime.parse("1985-07-30", DateTimeFormat.forPattern("yyyy-MM-dd")));
		contentBuilder.field("intro", "I like music & programming, sometimes singing with friends");
		contentBuilder.endObject();
		idxb.setSource(contentBuilder);
		IndexResponse idxr = idxb.execute().actionGet();
		System.out.println(idxr.isCreated());
		
	}
	
	@Test
	public void testGetDocument() {
		GetRequestBuilder getreq = client.prepareGet(IDX_NAME, TYPE_NAME, "000");
		getreq.setFields("name", "birthdate");
		GetResponse getresp = getreq.execute().actionGet();
		String name = getresp.getField("name").getValue().toString();
		DateTime birthdate = DateTime.parse(getresp.getField("birthdate").getValue().toString(), ISODateTimeFormat.dateOptionalTimeParser());
		System.out.println(name + birthdate.toString("yyyy-MM-dd"));
	}
	
	@Test
	public void testDeleteDocument() {
		DeleteRequestBuilder reqb = client.prepareDelete(IDX_NAME, TYPE_NAME, "000");
		DeleteResponse resp = reqb.execute().actionGet();
		System.out.println(resp.isFound());
	}
	
	@Test
	public void testSth() {
		String datestr = DateTime.parse("1985-07-30", DateTimeFormat.forPattern("yyyy-MM-dd")).toString();
		System.out.println(datestr);
		System.out.println(DateTime.parse(datestr));
	}
	
	@Test
	public void testUpdateDocument() throws IOException {
		UpdateRequestBuilder updateReq = client.prepareUpdate(IDX_NAME, TYPE_NAME, "000");
		updateReq.setDoc(jsonBuilder()
				.startObject()
				.field("gender", "male")
				.field("joindate", DateTime.parse("2013-02-28", DateTimeFormat.forPattern("yyyy-MM-dd")))
				.endObject());
		UpdateResponse resp = updateReq.execute().actionGet();
		System.out.println(resp.getVersion());
	}
	
	@Test
	public void testupsertDocument() throws IOException, InterruptedException, ExecutionException {
		IndexRequest idxReq = new IndexRequest(IDX_NAME, TYPE_NAME, "001");
		idxReq.source(jsonBuilder().startObject()
				.field("name", "李广增")
				.field("age", 28)
				.endObject());
		UpdateRequest uptReq = new UpdateRequest(IDX_NAME, TYPE_NAME, "001");
		uptReq.doc(jsonBuilder().startObject()
					.field("gender", "male").endObject())
				.upsert(idxReq);
		UpdateResponse uptResp = client.update(uptReq).get();
		System.out.println(uptResp.getVersion());
	}
	
	@Test
	public void testDeleteIndex() {
		DeleteIndexRequestBuilder reqb = client.admin().indices().prepareDelete(IDX_NAME);
		reqb.execute().actionGet();
	}
	
	@Test
	public void testSearchApi() {
		SearchRequestBuilder req = client.prepareSearch(IDX_NAME).setTypes(TYPE_NAME)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//				.setQuery(QueryBuilders.matchPhraseQuery("name", "李广增"))
				.setQuery(QueryBuilders.termQuery("name", "李广增"))
				.setPostFilter(FilterBuilders.rangeFilter("age").from(25).to(32))
				.setFrom(0).setSize(10).setExplain(true);
		SearchResponse resp = req.execute().actionGet();
		System.out.println(resp.getHits().getTotalHits());
	}
	
	@Test
	public void testBulkApi() throws IOException {
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		bulkRequest.add(getIndexRequest("王桐礼", "2013-06-03", "Architecture"));
		bulkRequest.add(getIndexRequest("王亮", "2013-03-27", "Technical Director"));
		BulkResponse resp = bulkRequest.execute().actionGet();
		System.out.println(resp.hasFailures());
	}
	
	private IndexRequestBuilder getIndexRequest(String name, String joindate, String title) throws IOException {
		IndexRequestBuilder req = client.prepareIndex(IDX_NAME, TYPE_NAME);
		req.setSource(jsonBuilder().startObject()
				.field("name", name)
				.field("joindate", DateTime.parse(joindate, DateTimeFormat.forPattern("yyyy-MM-dd")))
				.field("title", title)
				.endObject());
		return req;
	}
	
	/**
	 * Delete index and settings & mappings
	 */
	@After
	public void tearDown() {

	}

}
