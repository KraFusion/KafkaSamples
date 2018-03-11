package com.k5technologies.ps.kafka.samples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Kafka Sample
 * @author Joey Krabacher ( K5 Technologies)
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
        
        try {
        	// send over 25 messages
        	for (int i = 0; i < 25; i++) {
        		myProducer.send(new ProducerRecord<String, String>("my_topic", Integer.toString(i), "My Message " + Integer.toString(i)));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			myProducer.close();
		}
    }
}
