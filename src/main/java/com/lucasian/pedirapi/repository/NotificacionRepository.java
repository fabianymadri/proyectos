package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}