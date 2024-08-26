package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.EventoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoPedidoRepository extends JpaRepository<EventoPedido, Long> {
}