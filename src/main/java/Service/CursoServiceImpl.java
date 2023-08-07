package Service;

import models.Curso;
import repositories.CursoRepoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService{


    private CursoRepoImpl repojdbc;
    public CursoServiceImpl(Connection coneccion) {
        this.repojdbc = new CursoRepoImpl(coneccion);
    }


    @Override
    public List<Curso> listar() {

        try{
            return repojdbc.listar();
        }catch (SQLException throwable){
            throw new ServiceJdbcException(throwable.getMessage(),throwable.getCause());

        }
    }

    @Override
    public Optional<Optional<Curso>> porNombre(String nombre) {

        try{
            return Optional.ofNullable(Optional.ofNullable(repojdbc.porNombre(nombre)));
        }catch(SQLException throwable){
            throw new ServiceJdbcException(throwable.getMessage(),throwable.getCause());
        }



    }
}
