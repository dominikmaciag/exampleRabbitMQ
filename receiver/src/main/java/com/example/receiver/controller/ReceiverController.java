package com.example.receiver.controller;


import com.example.receiver.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {


    private final RabbitTemplate rabbitTemplate;

    public ReceiverController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/receiver")
    public String receiveMessage() {
        Object message = rabbitTemplate.receiveAndConvert("nowaKolejka");
        if (message != null) {
            return "nastąpiło zwrócenie z kolejki: " + rabbitTemplate.toString();
        } else {
            return "nie ma wiadomości do odczytu";
        }
    }


/*    @RabbitListener(queues = "nowaKolejka")
    public void listenerMessage(String message) {
        System.out.println(message);
    }*/

    @GetMapping("/notification")
    public ResponseEntity<Notification> receiveNotification() {
        Notification notification = rabbitTemplate.
                receiveAndConvert("nowaKolejka", ParameterizedTypeReference.forType(Notification.class));

        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.noContent().build();
    }


/*
    @RabbitListener(queues = "nowaKolejka")
    public void listenerMessage(Notification notification) {
        System.out.println(notification.getTitle());
    }
*/

}
