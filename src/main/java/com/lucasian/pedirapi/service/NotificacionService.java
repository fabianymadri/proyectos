package com.lucasian.pedirapi.service;

import com.lucasian.pedirapi.entity.Notificacion;
import com.lucasian.pedirapi.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void sendNotificacion(Notificacion notificacion) {
        // Lógica para enviar la notificación (por ejemplo, correo electrónico)

        notificacion.setStatus("SENT");
        notificacionRepository.save(notificacion);
    }

    public List<Notificacion> getAllNotificaions() {
        return notificacionRepository.findAll();
    }

    public Notificacion getNotificaionById(Long id) {
        return notificacionRepository.findById(id).get();
    }

    public Notificacion createNotification(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }
}
