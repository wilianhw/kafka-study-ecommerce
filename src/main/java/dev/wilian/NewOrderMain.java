package dev.wilian;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher()) {
            for (int i = 0; i < 10; i++) {
                String value = "value";
                String key = UUID.randomUUID().toString();
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var emailKey = UUID.randomUUID() + "@email";
                var email = "value";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", emailKey, email);
            }
        }
    }
}
