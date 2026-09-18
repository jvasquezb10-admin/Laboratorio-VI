package com.jvasquezb10.biblioteca.controller;



import com.jvasquezb10.biblioteca.service.LibroService;
import com.jvasquezb10.biblioteca.dto.LibroRequest;
import com.jvasquezb10.biblioteca.model.Libro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    

    @GetMapping
    public List<Libro> obtenerLibros() {
        return libroService.obtenerLibros();
    }

    @PostMapping
    public ResponseEntity<Libro> registrarLibro(@RequestBody LibroRequest request) {
        if (!libroService.datosValidos(request)) {
            return ResponseEntity.badRequest().build();
        }

        Libro libro = libroService.registrarLibro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(libro);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Libro> obtenerLibroPorTitulo(@PathVariable String titulo) {
        return libroService.obtenerLibroPorTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody LibroRequest request) {
        if (!libroService.datosValidos(request)) {
            return ResponseEntity.badRequest().build();
        }

        return libroService.actualizarLibro(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        if (libroService.eliminarLibro(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
