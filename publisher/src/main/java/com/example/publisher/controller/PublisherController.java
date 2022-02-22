package com.example.publisher.controller;


import com.example.publisher.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublisherController {


    private final RabbitTemplate rabbitTemplate;

    public PublisherController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/publisher")
    public String sendMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend("nowaKolejka", message);
        return "rzucono wiadomość na rabbitmq";
    }




    @PostMapping("/publisher")
    public String sendNotification(@RequestBody Notification notification) {
        rabbitTemplate.convertAndSend("nowaKolejka", notification);
        return "notyfikacja wysłana";
    }


}
