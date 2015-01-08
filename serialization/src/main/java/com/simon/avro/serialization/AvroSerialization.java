package com.simon.avro.serialization;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.simon.avro.schemas.User;

public class AvroSerialization {

	public static <T extends GenericRecord> void  serializeToFile(T obj, String path) throws IOException {
		DatumWriter<T> datumWriter = new SpecificDatumWriter<T>();
		DataFileWriter<T> dataFileWriter = new DataFileWriter<T>(datumWriter);
		dataFileWriter.create(obj.getSchema(), new File(path));
//		dataFileWriter.create(obj.getSchema(), new File("/tmp/obj.avro"));
		dataFileWriter.append(obj);
		dataFileWriter.close();
	}
	
	public static <T extends GenericRecord> byte[] serializeToByteArray(T obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		DatumWriter<T> datumWriter = new SpecificDatumWriter<T>(obj.getSchema());
		datumWriter.write(obj, encoder);
		encoder.flush();
		out.close();
		return out.toByteArray();
	}
	
	public static <T extends GenericRecord> T deserializeFromByteArray(Class<T> clz, byte[] bytes) throws IOException {
		DatumReader<T> reader = new SpecificDatumReader<T>(clz);
		Decoder decoder = DecoderFactory.get().binaryDecoder(bytes, null);
		T obj = reader.read(null, decoder);
		return obj;
	}
	
	public static <T extends GenericRecord> T deserializeFromFile(Class<T> clz, String path) throws IOException {
		DatumReader<T> reader = new SpecificDatumReader<T>(clz);
		DataFileReader<T> dataFileReader = new DataFileReader<T>(new File(path), reader);
		T obj = null;
		while (dataFileReader.hasNext()) {
			obj = dataFileReader.next();
		}
		dataFileReader.close();
		return obj;
	}
	
	public static void main(String[] args) throws IOException {
		User user1 = new User();
		user1.setName("Alyssa");
		user1.setFavoriteNumber(256);
		// Leave favorite color null
		User user2 = new User("Ben", 7, "red");
		
		User user3 = User.newBuilder()
				.setName("Charlie")
				.setFavoriteNumber(null)
				.setFavoriteColor("blue")
				.build();
		serializeToFile(user1, "/tmp/user1.avro");
		serializeToFile(user2, "/tmp/user2.avro");
		serializeToFile(user3, "/tmp/user3.avro");
		
		byte[] bytes = serializeToByteArray(user2);
		User user_2 = deserializeFromByteArray(User.class, bytes);
		System.out.println(user_2);
		
		User user_3 = deserializeFromFile(User.class, "/tmp/user3.avro");
		System.out.println(user_3);
	}
	
}
