package com.k5technologies.ps.kafka.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Kafka Sample
 * 
 * @author Joey Krabacher (K5 Technologies)
 *
 */
public class KafkaConsumerSubscriberApp {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("group.id", "test");

		KafkaConsumer<String, String> myConsumer = new KafkaConsumer<String, String>(props);
		
		ArrayList<String> topics = new ArrayList<String>();
		topics.add("test");
		topics.add("test2");
		
		myConsumer.subscribe(topics);
		
		try {
			while (true) {
				ConsumerRecords<String, String> records = myConsumer.poll(100);
				for(ConsumerRecord<String, String> record : records){
					// Process each record
					System.out.println(
						String.format(
							"Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s", 
							record.topic(), record.partition(), record.offset(), record.key(), record.value()
						)
					);
				}
			}
		} catch(Exception e){
			System.out.println(e);
		} finally {
			myConsumer.close();
		}
		
	}
}
