package com.jvasquezb10.cursos.service;

import com.jvasquezb10.cursos.dto.CursoRequest;
import com.jvasquezb10.cursos.model.Curso;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final List<Curso> cursos = new ArrayList<>();
    private Long siguienteId = 1L;

    public Curso crearCurso(CursoRequest request) {
        Curso curso = new Curso();

        curso.setId(siguienteId++);
        curso.setNombre(request.getNombre());
        curso.setCodigo(request.getCodigo());
        curso.setCreditos(request.getCreditos());
        curso.setEstado(request.getEstado());

        cursos.add(curso);

        return curso;
    }

    public List<Curso> obtenerCursos() {
        return cursos;
    }

    public Optional<Curso> obtenerCursoPorCodigo(String codigo) {
        return cursos.stream()
                .filter(curso -> curso.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public Optional<Curso> actualizarCurso(Long id, CursoRequest request) {
        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                curso.setNombre(request.getNombre());
                curso.setCodigo(request.getCodigo());
                curso.setCreditos(request.getCreditos());
                curso.setEstado(request.getEstado());

                return Optional.of(curso);
            }
        }

        return Optional.empty();
    }

    public boolean eliminarCurso(Long id) {
        return cursos.removeIf(curso -> curso.getId().equals(id));
    }

    public boolean datosValidos(CursoRequest request) {
        return request != null
                && request.getNombre() != null && !request.getNombre().isBlank()
                && request.getCodigo() != null && !request.getCodigo().isBlank()
                && request.getCreditos() != null
                && request.getEstado() != null && !request.getEstado().isBlank();
    }
}
