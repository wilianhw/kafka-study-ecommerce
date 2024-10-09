package dev.wilian;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class FraudDetectorServer {

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties());

        consumer.subscribe(Collections.singleton("ECOMMERCE_NEW_ORDER"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            if (records.isEmpty()) {
                continue;
            }
            if (!records.isEmpty()) {
                System.out.println("Processing new order, checking for fraud");
                records.forEach(record -> {
                    System.out.println("---------------------------------");
                    System.out.println("Key: " + record.key());
                    System.out.println("Value: " + record.value());
                    System.out.println("Topic: " + record.topic());
                    System.out.println("Partition: " + record.partition());
                    System.out.println("Offset: " + record.offset());
                    System.out.println("Timestamp: " + record.timestamp());
                });
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Processing finished");
            }
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorServer.class.getSimpleName());
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, FraudDetectorServer.class.getSimpleName() + "-" + UUID.randomUUID());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");

        return properties;
    }
}
