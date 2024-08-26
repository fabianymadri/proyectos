package com.lucasian.pedirapi.service;


import com.lucasian.pedirapi.entity.Inventario;
import com.lucasian.pedirapi.entity.Pedido;
import com.lucasian.pedirapi.entity.PedidoProducto;
import com.lucasian.pedirapi.entity.Producto;
import com.lucasian.pedirapi.repository.InventarioRepository;
import com.lucasian.pedirapi.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public void actualizarStock(Pedido pedido) {
        for (PedidoProducto pedidoProducto : pedido.getProductos()) {
            Producto producto = pedidoProducto.getProducto();
            Inventario inventario = inventarioRepository.findByProducto(producto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado en el inventario"));

            int nuevaCantidad = inventario.getCantidad() - pedidoProducto.getCantidad();
            inventario.setCantidad(nuevaCantidad);
            inventario.setFechaActualizacion(LocalDateTime.now());
            inventarioRepository.save(inventario);

            if (nuevaCantidad <= 5) {
                System.out.println("POCAS UNIDADES");
            }
        }
    }
}
