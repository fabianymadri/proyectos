package com.lucasian.pedirapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasian.pedirapi.entity.Pedido;
import com.lucasian.pedirapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoListener {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private PedidoRepository pedidoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "pedido-eventos", groupId = "inventario-group")
    public void consumirPedido(String mensaje) {
        Pedido pedido = deserializarPedido(mensaje);
        inventarioService.actualizarStock(pedido);
    }

    @Transactional
    private Pedido deserializarPedido(String mensaje) {
        try {
            Pedido pedido = null;
            Optional<Pedido> pedidos = Optional.of(new Pedido());
            String codigo = mensaje.substring(mensaje.length() - 1);
            pedidos = pedidoRepository.findById(Long.valueOf(codigo));

            pedido = pedidos.get();
            return pedido;
        } catch (Exception e) {
            System.out.println("error al deserializar " + e);
            return null;
        }
    }
}
