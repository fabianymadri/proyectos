package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}