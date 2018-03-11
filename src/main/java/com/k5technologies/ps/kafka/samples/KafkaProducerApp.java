package com.k5technologies.ps.kafka.samples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Kafka Sample
 *
 */
public class KafkaProducerApp 
{
    public static void main( String[] args )
    {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093, localhost:9094");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
        
        ProducerRecord<String, String> myRecord = new ProducerRecord<String, String>("my_topic", "My-Message_Key", "My Message 3");
        
        try {
			myProducer.send(myRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        myProducer.close();
    }
}