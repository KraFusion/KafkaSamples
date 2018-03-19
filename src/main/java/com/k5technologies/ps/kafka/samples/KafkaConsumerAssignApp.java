package com.k5technologies.ps.kafka.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * Kafka Sample
 * 
 * @author Joey Krabacher (K5 Technologies)
 *
 *
 * e.g. producers to test:
 * bin/kafka-producer-perf-test.sh --topic test --num-records 50 --record-size 1 --throughput 10 --producer-props bootstrap.servers=localhost:9092 key.serializer=org.apache.kafka.common.serialization.StringSerializer value.serializer=org.apache.kafka.common.serialization.StringSerializer
 * bin/kafka-producer-perf-test.sh --topic test2 --num-records 50 --record-size 1 --throughput 10 --producer-props bootstrap.servers=localhost:9092 key.serializer=org.apache.kafka.common.serialization.StringSerializer value.serializer=org.apache.kafka.common.serialization.StringSerializer
 */
public class KafkaConsumerAssignApp {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> myConsumer = new KafkaConsumer<String, String>(props);
		
		ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
		TopicPartition testTopicPart0 = new TopicPartition("test", 0);
		TopicPartition test2TopicPart2 = new TopicPartition("test2", 2);
		
		partitions.add(testTopicPart0);
		partitions.add(test2TopicPart2);
		
		myConsumer.assign(partitions);
		
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
