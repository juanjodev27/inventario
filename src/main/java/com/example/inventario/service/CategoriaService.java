package com.example.inventario.service;

import com.example.inventario.entity.Categoria;
import com.example.inventario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private static final String MSG_NO_ENCONTRADO = "Categoria no encontrado con el ID proporcionado";

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    public Categoria crear(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria obtenerPorId(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));
    }

    public Categoria actualizarPorId(Long id, Categoria categoria){
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_NO_ENCONTRADO));
        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(existente);
    }

    public void eliminarPorId(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new RuntimeException(MSG_NO_ENCONTRADO);
        }
        categoriaRepository.deleteById(id);
    }
}
