<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, models.Curso" %>

<%

  Map<String,String> errores = (Map<String,String>)  request.getAttribute("errores");
  Curso curso = (Curso) request.getAttribute("curso");


%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Formulario Cursos</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: #ffffff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 5px;
    }

    label {
      font-weight: bold;
      display: block;
      margin-bottom: 5px;
    }

    input, select {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }

    button {
      background-color: #007bff;
      color: #fff;
      border: none;
      padding: 10px 20px;
      border-radius: 4px;
      cursor: pointer;
    }

    button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Formulario JSP</h1>

  <form action="<%=request.getContextPath()%>/formServlet" method="post">
    <div>
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" value="<%=curso != null && curso.getNombre() != null ? curso.getNombre():""%>">
      <% if(errores != null && errores.containsKey("nombre")) { %>
      <div style="color:#690606"><%=errores.get("nombre") %></div>
      <% } %>
    </div>
    <div>
      <label for="descripcion">Descripción:</label>
      <input type="text" id="descripcion" name="descripcion" value="<%= curso != null && curso.getDescripcion() != null ? curso.getDescripcion():"" %>">
      <% if(errores != null && errores.containsKey("descripcion")) { %>
      <div style="color:#d21414"><%=errores.get("descripcion") %></div>
      <% } %>
    </div>
    <div>
      <label for="instructor">Instructor:</label>
      <input type="text" id="instructor" name="instructor" value="<%=curso != null && curso.getInstructor() != null ? curso.getInstructor():""%>">
      <% if(errores != null && errores.containsKey("instructor")) { %>
      <div style="color:#a11515"><%=errores.get("instructor") %></div>
      <% } %>
    </div>
    <div>
      <label for="duracion">Duración:</label>
      <input type="number" id="duracion" name="duracion"  step="0.01" value="<%=curso != null && curso.getDuracion() != null ? curso.getDuracion():""%>">
      <% if(errores != null && errores.containsKey("duracion")) { %>
      <div style="color:#a11515"><%=errores.get("duracion") %></div>
      <% } %>
    </div>

    <div><input type="submit" value="<%=(curso != null && curso.getId() != null && curso.getId() > 0) ? "Editar" : "Crear"%>"></div>
    <input type="hidden" name="id" value="<%=curso != null ? curso.getId() : ""%>">
  </form>

</div>
</body>
</html>