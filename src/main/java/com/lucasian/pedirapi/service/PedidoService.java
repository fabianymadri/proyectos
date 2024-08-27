package com.lucasian.pedirapi.service;

import com.lucasian.pedirapi.entity.Cliente;
import com.lucasian.pedirapi.entity.Pedido;
import com.lucasian.pedirapi.repository.ClienteRepository;
import com.lucasian.pedirapi.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        Pedido nuevoPedido = null;
        List<Cliente> clientes =  clienteRepository.findAll();
        Optional<Cliente> clienteOptional = clientes.stream()
                .filter(cliente -> cliente.getNombre().equals(pedido.getUsuarioCreacion()))
                .findFirst();

        if (clienteOptional.isPresent()) {
            pedido.setTotal(pedido.getPrecioUnitario().multiply(new BigDecimal(pedido.getCantidad())));
            pedido.setCliente(clienteOptional.get());
            nuevoPedido =  pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Cliente no encontrado con el nombre: " + pedido.getUsuarioCreacion());
        }
        String mensaje = "Nuevo pedido creado: " + nuevoPedido.getId();
        kafkaTemplate.send("pedido-eventos", mensaje);

        return nuevoPedido;
    }


}
