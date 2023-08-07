package Service;

import models.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    Optional<Optional<Curso>> porNombre(String nombre);
}
