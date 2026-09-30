package com.example.jpa_empleos.controllers;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriasRepository categoriasRepo;

    public CategoriaController(CategoriasRepository categoriasRepo) {
        this.categoriasRepo = categoriasRepo;
    }

    @GetMapping
    public Iterable<Categoria> obtenerTodas() {
        return categoriasRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Integer id) {

        Optional<Categoria> categoria = categoriasRepo.findById(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {

        Categoria nuevaCategoria = categoriasRepo.save(categoria);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @PathVariable Integer id,
            @RequestBody Categoria categoria) {

        Optional<Categoria> categoriaBuscada = categoriasRepo.findById(id);

        if (categoriaBuscada.isPresent()) {

            Categoria categoriaActual = categoriaBuscada.get();

            categoriaActual.setNombre(categoria.getNombre());
            categoriaActual.setDescripcion(categoria.getDescripcion());

            Categoria categoriaActualizada =
                    categoriasRepo.save(categoriaActual);

            return ResponseEntity.ok(categoriaActualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (categoriasRepo.existsById(id)) {

            categoriasRepo.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}