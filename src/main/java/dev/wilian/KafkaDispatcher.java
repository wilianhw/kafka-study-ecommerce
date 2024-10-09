package dev.wilian;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

class KafkaDispatcher implements Closeable {

    private final KafkaProducer<String, String> producer;

    KafkaDispatcher() {
        this.producer = new KafkaProducer<>(properties());
    }

    void send(String topic, String key, String value) throws ExecutionException, InterruptedException {
        Callback callback = (res, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.printf("Topic::" + res.topic() + "\nParticion/" + res.partition() + "\nOffset/" + res.offset() + "\nTimestamp/" + res.timestamp());
        };

        ProducerRecord<String, String> recordNewOrder = new ProducerRecord<>(topic, key, value);
        producer.send(recordNewOrder, callback).get();
    }

    private static Properties properties() {
        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    @Override
    public void close() {
        producer.close();
    }
}
