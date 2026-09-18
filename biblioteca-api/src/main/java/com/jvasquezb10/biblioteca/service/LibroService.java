package com.jvasquezb10.biblioteca.service;


import com.jvasquezb10.biblioteca.dto.LibroRequest;
import com.jvasquezb10.biblioteca.model.Libro;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;


@Service
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private Long siguienteId = 1L;


    public Libro registrarLibro(LibroRequest request) {

        Libro libro = new Libro();

        libro.setId(siguienteId++);
        libro.setTitulo(request.getTitulo());
        libro.setAutor(request.getAutor());
        libro.setIsbn(request.getIsbn());
        libro.setAnioPublicacion(request.getAnioPublicacion());
        libro.setEstado(request.getEstado());

        libros.add(libro);

        return libro;
    }

    public List<Libro> obtenerLibros() {
        return libros;
    }

    public Optional<Libro> obtenerLibroPorTitulo(String titulo) {
        return libros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public Optional<Libro> actualizarLibro(Long id, LibroRequest request) {
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                libro.setTitulo(request.getTitulo());
                libro.setAutor(request.getAutor());
                libro.setIsbn(request.getIsbn());
                libro.setAnioPublicacion(request.getAnioPublicacion());
                libro.setEstado(request.getEstado());

                return Optional.of(libro);
            }
        }

        return Optional.empty();
    }

    public boolean eliminarLibro(Long id) {
        return libros.removeIf(libro -> libro.getId().equals(id));
    }

    public boolean datosValidos(LibroRequest request) {
        return request != null
                && request.getTitulo() != null && !request.getTitulo().isBlank()
                && request.getAutor() != null && !request.getAutor().isBlank()
                && request.getIsbn() != null && !request.getIsbn().isBlank()
                && request.getAnioPublicacion() != null
                && request.getEstado() != null && !request.getEstado().isBlank();
    }
}
