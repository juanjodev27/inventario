package com.example.inventario.service;

import com.example.inventario.entity.Producto;
import com.example.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private static final String MSG_NO_ENCONTRADO  = "Producto no encontrado con el ID proporcionado";

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){

        this.productoRepository = productoRepository;
    }

    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    public Producto crear(Producto producto){

        if(productoRepository.findByCodigo(producto.getCodigo()).isPresent()){
            throw new RuntimeException("Ya existe un producto con el código: " + producto.getCodigo());
        }
        return productoRepository.save(producto);
    }

    public Producto buscarPorId(Long id){

        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));
    }

    public Producto actualizarPorId(Long id, Producto producto){
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));

        existente.setCodigo(producto.getCodigo());
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());

        return productoRepository.save(existente);
    }

    public void eliminarPorId(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException(MSG_NO_ENCONTRADO);
        }
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorNombre(String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Producto> buscarPorCodigo(String codigo){
        return  productoRepository.findByCodigo(codigo);
    }
}
