package com.simon.avro.serialization;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

public class AvroGenericSerialization {
	
	public static byte[] serialize(String avscpath, GenericRecord record) throws IOException {
		File file = new File(avscpath);
		Schema schema = new Schema.Parser().parse(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		datumWriter.write(record, encoder);
		encoder.flush();
		out.close();
		return out.toByteArray();
	}
	
	public static GenericRecord deserialize(String avscpath, byte[] bytes) throws IOException {
		File file = new File(avscpath);
		Schema schema = new Schema.Parser().parse(file);
		Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		GenericRecord obj = datumReader.read(null, decoder);
		return obj;
		
	}

	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String userDir = System.getProperty("user.dir");
		String path = userDir + "/src/avsc/user.avsc";
		Schema schema = new Schema.Parser().parse(new File(path));
		GenericRecord record = new GenericData.Record(schema);
		record.put("name", "Alyssa");
		record.put("favorite_number", 256);
		// Leave favorite color null
		
		GenericRecord user2 = new GenericData.Record(schema);
		user2.put("name", "Ben");
		user2.put("favorite_number", 7);
		user2.put("favorite_color", "red");
		
		byte[] bytes = serialize(path, record);
		GenericRecord record_0 = deserialize(path, bytes);
		System.out.println(record_0);
	}
	
}
