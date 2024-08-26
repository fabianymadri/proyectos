package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}