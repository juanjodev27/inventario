package com.example.inventario.service;

import com.example.inventario.entity.Producto;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public Producto crearProducto(Producto producto){

        if(productoRepository.findByCodigo(producto.getCodigo()).isPresent()){
            throw new RuntimeException("Ya existe un producto con el código: " + producto.getCodigo());
        }
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto actualizarProductoPorId(Long id, Producto producto){
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto con ID no encontrado"));

        existente.setCodigo(producto.getCodigo());
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());

        return productoRepository.save(existente);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto con id no encontrado");
        }
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarProductoPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Producto> buscarPorCodigo(String codigo){
        return  productoRepository.findByCodigo(codigo);
    }
}
