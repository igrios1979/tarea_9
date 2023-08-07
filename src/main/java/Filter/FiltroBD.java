package Filter;

import Service.ServiceJdbcException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import util.ConexionJDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class FiltroBD implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try(Connection conn = ConexionJDBC.getConnection()){
            // Intenta obtener una conexión a la base de datos utilizando la clase "ConexionJDBC".
            // La sintaxis "try-with-resources" se utiliza aquí para asegurarse de que la conexión se cierre automáticamente después del bloque.

            if (conn.getAutoCommit()) {
                // Comprueba si la conexión tiene habilitada la confirmación automática (auto-commit).
                // Si está habilitada, significa que cada instrucción SQL se confirma automáticamente después de ejecutarse.
                conn.setAutoCommit(false);
                // Deshabilita la confirmación automática para permitir el control manual de transacciones.
                // Esto significa que las transacciones deberán confirmarse explícitamente para que los cambios sean permanentes.
            }

            try {
                servletRequest.setAttribute("conn",conn);
                // Establece el atributo "conn" en el objeto "servletRequest".
                // Esto permite que otros componentes o servlets accedan a la conexión de la base de datos durante la solicitud actual.

                filterChain.doFilter(servletRequest, servletResponse);
                // Invoca el método "doFilter" de "filterChain" para continuar con el procesamiento de la solicitud y la respuesta.
                // En otras palabras, permite que la solicitud siga fluyendo a través de otros filtros o llegue al servlet final.

                conn.commit();
                // Confirma la transacción, es decir, guarda los cambios realizados en la base de datos.
                // Esto se ejecuta solo si no se lanzó una excepción en el bloque "try" anidado.

            } catch (SQLException | ServiceJdbcException e) {
                // Captura cualquier excepción de tipo SQLException que pueda ocurrir durante el procesamiento de la solicitud.

                conn.rollback();
                // Realiza un "rollback" de la transacción en caso de una SQLException.
                // Un "rollback" deshace cualquier cambio realizado en la base de datos durante la transacción actual.

                ((HttpServletResponse)servletResponse).sendError(500, "Error 500 --->>" + e.getMessage());
                // Envía una respuesta de error HTTP con código 500 (Error interno del servidor).
                // Junto con el mensaje de error de la excepción SQLException.

            }
        } catch (SQLException e) {
            // Captura cualquier excepción de tipo SQLException que pueda ocurrir fuera del bloque "try" anidado.

            throw new RuntimeException(e);
            // Lanza una nueva excepción de tipo RuntimeException con la SQLException capturada como causa.
            // Al hacer esto, se propagará la excepción hacia arriba en la jerarquía de llamadas del programa,
            // lo que puede llevar a la terminación del programa o a un manejo adicional de la excepción en un nivel superior.

        }
    }
}
