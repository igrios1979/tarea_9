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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/formServlet")
public class CursoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService cursoService = new CursoServiceImpl(conn);


        Long id;
        try{
            id=Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        Curso curso = new Curso();

        if (id > 0) {
            Optional<Curso> cursoOptional = cursoService.porId(id);
            curso = cursoOptional.orElse(curso);
        }

        req.setAttribute("curso", curso);
        req.getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService cursoService = new CursoServiceImpl(conn);

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String instructor = req.getParameter("instructor");

        Double duracion ;


        //Long id;
        try{
            duracion =Double.valueOf(req.getParameter("duracion"));;
        }catch (NumberFormatException e){
            duracion = 0.0;
        }

        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isEmpty()) {
            errores.put("nombre", "Ingrese un nombre");
        }

        if (descripcion == null || descripcion.isEmpty()) {
            errores.put("descripcion", "Ingrese una descripción");
        }

        if (instructor == null || instructor.isEmpty()) {
            errores.put("instructor", "Ingrese un instructor");
        }

        if (duracion <= 0) {
            errores.put("duracion", "Ingrese una duración válida");
        }

      Long id;
        try{
            id=Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }


        Curso curso = new Curso(  id, nombre, descripcion, instructor, duracion);

        if (errores.isEmpty()) {
            cursoService.guardar(curso);
            resp.sendRedirect(req.getContextPath() + "/buscar_curso");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("curso", curso);
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }



}
