package com.fuzzystudio.todo.rmq;

import com.fuzzystudio.todo.controller.ToDoController;
import com.fuzzystudio.todo.domain.ToDo;
import com.fuzzystudio.todo.repository.ToDoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class ToDoConsumer {
    private Logger log = LoggerFactory.getLogger(ToDoController.class);

    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${todo.amqp.queue}")
    public void processToDo(ToDo toDo) {
        log.info("Consumer> " + toDo);
        log.info("ToDo created> " + this.repository.save(toDo));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
