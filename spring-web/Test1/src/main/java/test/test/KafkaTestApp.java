package test.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaTestApp {

    //Необходимо, чтобы заказы мы создавали не посредством контроллера Ордерс, а посредством
    // чтения сообщения из топика Order в кафка.

    public static void main(String[] args) {

        SpringApplication.run(KafkaTestApp.class, args);

    }
}
