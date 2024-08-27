package dev.wilian;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties());

        Callback callback = (res, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.printf("Topic::" + res.topic() + "\nParticion/" + res.partition() + "\nOffset/" + res.offset() + "\nTimestamp/" + res.timestamp());
        };

        String value = "value";
        String key = UUID.randomUUID().toString();
        ProducerRecord<String, String> recordNewOrder = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key, value);
        producer.send(recordNewOrder, callback).get();

        var email = "key@email, value@email";
        ProducerRecord<String, String> recordSendEmail = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", email, email);
        producer.send(recordSendEmail, callback).get();
    }

    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }
}
