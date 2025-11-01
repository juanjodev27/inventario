package com.example.inventario.controller;

import com.example.inventario.entity.Categoria;
import com.example.inventario.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService  = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        List<Categoria> categorias  = categoriaService.listar();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria){
        Categoria categoriaGuardada = categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarPorId(@PathVariable Long id, @RequestBody Categoria categoria){
        Categoria actualizado = categoriaService.actualizarPorId(id, categoria);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id){
        categoriaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
