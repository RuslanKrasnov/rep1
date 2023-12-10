package test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestKafka {

    @Qualifier(value = "KafkaTest")
    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @PostMapping
    public void sendOrder(){
 //       kafkaTemplate.send("GeekTopicForUsers", 1L, new UserDto("Bob", "Parker", 35));
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("GeekTopicForUsers", new UserDto("Bob", "Parker", 35));
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }



}
