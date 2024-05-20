## Summary

This project was created for my studies of Kafka. Its goal was to put the theory into practice.

## Pre-requisites

- **Java 17**
- **Maven**
- **Apache Kafka**

## Running Locally

### Apache Kafka

**Linux:**

1. To install Apache Kafka, go to this link: [Apache Kafka Downloads](https://kafka.apache.org/downloads) and download Kafka.
2. Unzip the file and navigate to the root directory.

3. Run the following commands to start Kafka:

   ```sh
   ./bin/zookeeper-server-start.sh config/zookeeper.properties
   ```

   This command starts Zookeeper. After it is running, start Kafka with:

   ```sh
   ./bin/kafka-server-start.sh config/server.properties
   ```

### Running using IntelliJ IDEA

1. Open the project using IntelliJ IDEA and wait for the dependencies to be resolved.
2. There are some main static classes; you can run each of them directly from the IDE.

## Project Link

For more details and to view the project repository, visit [kafka-study-ecommerce](https://github.com/wilianhw/kafka-study-ecommerce).