package com.example.inventario.controller;

import com.example.inventario.entity.Proveedor;
import com.example.inventario.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> listar(){
        List<Proveedor> proveedores = proveedorService.listar();
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor proveedor){
        Proveedor proveedorGuardado = proveedorService.crear(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id){
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        return ResponseEntity.ok(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarPorId(@PathVariable Long id, @RequestBody Proveedor proveedor){
        Proveedor actualizado = proveedorService.actualizarPorId(id,proveedor);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id){
        proveedorService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
