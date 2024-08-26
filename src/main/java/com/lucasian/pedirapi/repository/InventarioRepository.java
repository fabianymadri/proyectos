package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.Inventario;
import com.lucasian.pedirapi.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByProducto(Producto producto);
}