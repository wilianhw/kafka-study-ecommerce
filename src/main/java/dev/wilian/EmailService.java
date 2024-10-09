package dev.wilian;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        var kafkaService = new KafkaService("ECOMMERCE_SEND_EMAIL", emailService::parse);
        kafkaService.run();


    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("---------------------------------");
        System.out.println("Key: " + record.key());
        System.out.println("Value: " + record.value());
        System.out.println("Topic: " + record.topic());
        System.out.println("Partition: " + record.partition());
        System.out.println("Offset: " + record.offset());
        System.out.println("Timestamp: " + record.timestamp());
    }
}
