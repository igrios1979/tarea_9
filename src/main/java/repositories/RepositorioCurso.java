package repositories;

import java.sql.SQLException;
import java.util.List;

public interface RepositorioCurso <T>{

    List<T> listar() throws SQLException;
    T porNombre(String nombre) throws SQLException;

}
