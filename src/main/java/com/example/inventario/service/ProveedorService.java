package com.example.inventario.service;

import com.example.inventario.entity.Proveedor;
import com.example.inventario.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private static final String MSG_NO_ENCONTRADO = "Proveedor no encontrado con el ID proporcionado";

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listar(){
        return proveedorRepository.findAll();
    }

    public Proveedor crear(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public Proveedor obtenerPorId(Long id){
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));
    }

    public Proveedor actualizarPorId(Long id, Proveedor proveedor){
        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));
        existente.setNombre(proveedor.getNombre());
        existente.setEmail(proveedor.getEmail());
        existente.setTelefono(proveedor.getTelefono());
        existente.setDireccion(proveedor.getDireccion());
        return proveedorRepository.save(existente);
    }

    public void eliminarPorId(Long id){
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException(MSG_NO_ENCONTRADO);
        }

        proveedorRepository.deleteById(id);
    }
}
