<%@ page contentType="UTF-8" import="java.util.List, models.Curso" %>


<%
    List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");

%>


    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Listado productos</title>
            <style>
                body {
                font-family: Arial, sans-serif;
                }

                h1 {
                color: #0066cc;
                }

                table {
                width: 100%;
                border-collapse: collapse;
                }

                th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
                }

                th {
                background-color: #f2f2f2;
                }

                tr:nth-child(even) {
                background-color: #f2f2f2;
                }

                a {
                color: #0066cc;
                text-decoration: none;
                }

                a:hover {
                text-decoration: underline;
                }

                .username {
                font-weight: bold;
                }
            </style>
        </head>

        <body>
            <h1>Lista Cursos</h1>

            <form id="busqueda-form" action="${pageContext.request.contextPath}/buscar_nombre" method="GET">
                <label for="curso">Curso:</label>
                <input type="text" id="curso" name="curso" placeholder="Nombre del curso">
                <button type="submit" id="buscar-btn">Buscar</button>
            </form>
            <div>
  <br></br>
            </div>

            <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th>Instructor</th>
                    <th>Duracion</th>

                </tr>
                <% for (Curso c : cursos) { %>
                <tr>
                    <td><%= c.getId() %></td>
                    <td><%= c.getNombre() %></td>
                    <td><%= c.getDescripcion()%></td>
                    <td><%= c.getInstructor()%></td>
                    <td><%= c.getDuracion()%></td>

                                 </tr>
                <% } %>
            </table>
        </body>
    </html>