package com.jvasquezb10.cursos.controller;

import com.jvasquezb10.cursos.dto.CursoRequest;
import com.jvasquezb10.cursos.model.Curso;
import com.jvasquezb10.cursos.service.CursoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> obtenerCursos() {
        return cursoService.obtenerCursos();
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoRequest request) {
        if (!cursoService.datosValidos(request)) {
            return ResponseEntity.badRequest().build();
        }

        Curso curso = cursoService.crearCurso(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Curso> obtenerCursoPorCodigo(@PathVariable String codigo) {
        return cursoService.obtenerCursoPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @RequestBody CursoRequest request) {
        if (!cursoService.datosValidos(request)) {
            return ResponseEntity.badRequest().build();
        }

        return cursoService.actualizarCurso(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        if (cursoService.eliminarCurso(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}