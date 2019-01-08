package com.fuzzystudio.todo.config;

import com.fuzzystudio.todo.domain.ToDo;
import com.fuzzystudio.todo.rmq.ToDoProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoSender {
    @Bean
    public CommandLineRunner sendToDos(@Value("${todo.amqp.queue}") String destination, ToDoProducer producer){
        return args -> {
            for (int i = 0; i < 10000; i++) {
                producer.sendTo(destination,new ToDo("workout tomorrow morning!"));
                Thread.sleep(100);
            }
        };
    }
}
