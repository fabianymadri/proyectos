package com.lucasian.pedirapi.controller;

import com.lucasian.pedirapi.entity.Notificacion;
import com.lucasian.pedirapi.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> getAllNotifications() {
        return notificacionService.getAllNotificaions();
    }

    @GetMapping("/{id}")
    public Notificacion getNotificationById(@PathVariable Long id) {
        return notificacionService.getNotificaionById(id);
    }

    @PostMapping
    public Notificacion createNotification(@RequestBody Notificacion notificacion) {
        return notificacionService.createNotification(notificacion);
    }
}
