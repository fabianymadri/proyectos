package com.lucasian.pedirapi.service;

import com.lucasian.pedirapi.entity.Pedido;
import com.lucasian.pedirapi.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        Pedido nuevoPedido = pedidoRepository.save(pedido);

        String mensaje = "Nuevo pedido creado: " + nuevoPedido.getId();
        kafkaTemplate.send("pedido-eventos", mensaje);

        return nuevoPedido;
    }

}
