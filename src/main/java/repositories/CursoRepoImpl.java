package repositories;

import models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoRepoImpl implements RepositorioCurso<Curso> {

    private Connection conn;

    public CursoRepoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Curso> listar() throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM cursos")) {
            while (rs.next()) {
                Curso curso = getCurso(rs);
                cursos.add(curso);
            }
        }

        return cursos;
    }

    @Override
    public Curso porNombre(String nombre) throws SQLException {
        Curso curso = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM cursos AS c WHERE c.nombre LIKE ?")) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    curso = getCurso(rs);
                }
            }
        }
        return curso;
    }



    public List<Curso> porNombreLike(String nombre) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM cursos AS c WHERE c.nombre LIKE ?")) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = getCurso(rs);
                    cursos.add(curso);
                }
            }
        }
        return cursos;
    }


    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setId(rs.getLong("id"));
        curso.setNombre(rs.getString("nombre"));
        curso.setDescripcion(rs.getString("description"));
        curso.setInstructor(rs.getString("instructor"));
        curso.setDuracion(rs.getDouble("duration"));
        return curso;
    }


    @Override
    public void guardar(Curso curso) throws SQLException {
        String sql;
        if(curso.getId() != null && curso.getId() >0){
            sql = "update cursos set nombre=?, description=?, instructor=?, duration =? where id=?";
        }else{
            sql = "insert into cursos (nombre,description,instructor,duration) values (?,?,?,?)";
        }
        try(PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setString(1,curso.getNombre());
            stm.setString(2,curso.getDescripcion());
            stm.setString(3,curso.getInstructor());
            stm.setDouble(4,curso.getDuracion());
            if(curso.getId() != null && curso.getId() >0) {
                stm.setLong(5, curso.getId());
            }
            stm.executeUpdate();
        }// fin try
    }

    @Override
    public Curso porId(Long id) throws SQLException {
       Curso curso = null;
       try(PreparedStatement stmt = conn.prepareStatement("select * from cursos c where c.id = ?")){
           stmt.setLong(1,id);

       try(ResultSet rs = stmt.executeQuery()){
                                   if(rs.next()){
                                             curso = getCurso(rs);
                                               }
                                               }
       }
      return curso;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from cursos where id=?";
        try(PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setLong(1,id);
            stm.executeUpdate();

        }

    }





}
