package com.lucasian.pedirapi.service;


import com.lucasian.pedirapi.entity.Notificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacionListener {

    @Autowired
    private NotificacionService notificacionService;

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consumeOrderEvents(String message) {
        Notificacion notification = new Notificacion();
        notification.setOrderId(Long.parseLong("1"));
        notification.setNotificationType("EMAIL");
        notification.setMessage("Nuevo pedido recibido con ID: " + notification.getOrderId());

        notificacionService.createNotificacion(notification);
        notificacionService.sendNotificacion(notification);
    }
}
