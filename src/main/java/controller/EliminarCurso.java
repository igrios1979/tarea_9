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
import java.util.Optional;

@WebServlet("/EliminaCur")
public class EliminarCurso extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService serv = new CursoServiceImpl(conn);

        long id;
        try{
            id=Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0L;
        }

        if(id>0){
            Optional<Curso> c = serv.porId(id);
            if(c.isPresent()){
                serv.eliminar(id);
                resp.sendRedirect(req.getContextPath()+"/buscar_curso");
            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"No existe el curso");
            }

        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND,"Falta de parametro Por URL ");
        }
    }
}
