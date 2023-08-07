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

    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setId(rs.getLong("id"));
        curso.setNombre(rs.getString("nombre"));
        curso.setDescripcion(rs.getString("description"));
        curso.setInstructor(rs.getString("instructor"));
        curso.setDuracion(rs.getDouble("duration"));
        return curso;
    }
}
