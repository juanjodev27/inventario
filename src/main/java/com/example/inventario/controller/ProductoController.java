package com.example.inventario.controller;

import com.example.inventario.entity.Producto;
import com.example.inventario.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listar();
              return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto){
        Producto productoGuardado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id){
        Producto producto = productoService.buscarPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProductoPorId(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoActualizado = productoService.actualizarPorId(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre){
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> buscarPorCodigo(@PathVariable  String codigo){
        return productoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
