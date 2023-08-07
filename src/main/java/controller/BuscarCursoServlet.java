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
import java.util.List;

@WebServlet("/buscar_curso")
public class BuscarCursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService cs = new CursoServiceImpl(conn);

        List<Curso> cursos = cs.listar();

        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("cursos",cursos);
        getServletContext().getRequestDispatcher("/vista_curso.jsp").forward(req,resp);

    }
}
