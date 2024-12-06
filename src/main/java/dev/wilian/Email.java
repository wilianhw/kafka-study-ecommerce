package dev.wilian;

public class Email {
    private final String subject, body;

    public Email(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("Subject: %s - Body: %s", subject, body);
    }
}
