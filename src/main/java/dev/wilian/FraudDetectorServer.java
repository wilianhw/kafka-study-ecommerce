package dev.wilian;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorServer {

    public static void main(String[] args) {
        FraudDetectorServer fraudDetectorServer = new FraudDetectorServer();
        try (KafkaService kafkaService = new KafkaService<Order>(
                FraudDetectorServer.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudDetectorServer::parse,
                Order.class
        )) {
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("---------------------------------");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Topic: " + record.topic());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        System.out.println("Timestamp: " + record.timestamp());
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
