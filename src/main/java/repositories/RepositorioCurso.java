package repositories;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioCurso <T>{

    List<T> listar() throws SQLException;
    T porNombre(String nombre) throws SQLException;

    void guardar(T t) throws SQLException;

    T porId(Long id) throws SQLException;

    void eliminar(Long id) throws SQLException;
}
