package com.lucasian.pedirapi.repository;

import com.lucasian.pedirapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}