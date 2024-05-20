package dev.wilian;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties());

        String value = "key 4, value 4";
        ProducerRecord<String, String> record = new ProducerRecord<>("NEW_ORDER", value, value);

        producer.send(record, (res, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.printf("Topic::" + res.topic() + "\nParticion/" + res.partition() + "\nOffset/" + res.offset() + "\nTimestamp/" + res.timestamp());
        }).get();
    }

    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }
}
