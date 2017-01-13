package servlets;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasarActivoInactivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            Sardine sardine = SardineFactory.begin("webdavuser", "password");

            String urlOrigen = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/activos/" + id;
            String urlDestino = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/inactivos/" + id;

            int number = 1;
            while (sardine.exists(urlDestino)) {
                int posicion = id.lastIndexOf(".");
                if (posicion > 0) {
                    id = id.substring(0, posicion) + "_" + number + id.substring(posicion);
                } else {
                    id = id + "_" + number;
                }
            }

            urlDestino = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/inactivos/" + id;

            sardine.move(urlOrigen, urlDestino);
            response.sendRedirect("ContenidoExtraInactivo?msg=okMov");

        } catch (Exception e) {
            request.setAttribute("error_pasar", "No es posible pasar el fichero de inactivo a activo");
            RequestDispatcher rd = request.getRequestDispatcher("contenidoExtra.jsp");
            rd.forward(request, response);
        }        
    }
}
