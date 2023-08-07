package controller;

import Service.CursoService;
import Service.CursoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Curso;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet("/buscar_nombre")
public class ListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService cs = new CursoServiceImpl(conn);

        String nombre = req.getParameter("curso");
        List<Curso> listacurso = new ArrayList<>();
        Optional<Optional<Curso>> cursosOptional = cs.porNombre(nombre);
        Optional<Curso> cursoOptional = cursosOptional.flatMap(opt -> opt);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            listacurso.add(curso);
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("cursos",listacurso);
            getServletContext().getRequestDispatcher("/vista_curso.jsp").forward(req,resp);
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("cursos",listacurso);
            getServletContext().getRequestDispatcher("/vista_curso.jsp").forward(req,resp);

        }


    }
}