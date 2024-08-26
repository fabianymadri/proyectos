package com.lucasian.pedirapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasian.pedirapi.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoListener {

    @Autowired
    private InventarioService inventarioService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "pedido-eventos", groupId = "inventario-group")
    public void consumirPedido(String mensaje) {
        Pedido pedido = deserializarPedido(mensaje);
        inventarioService.actualizarStock(pedido);
    }

    private Pedido deserializarPedido(String mensaje) {
        try {
            return objectMapper.readValue(mensaje, Pedido.class);
        } catch (Exception e) {
            System.out.println("error al deserializar " + e);
            return null;
        }
    }
}
