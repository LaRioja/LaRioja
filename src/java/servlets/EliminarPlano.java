package servlets;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminarPlano extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            Sardine sardine = SardineFactory.begin();
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/plano/" + id;
            if (sardine.exists(url)) {
                sardine.delete(url);
                response.sendRedirect("PlanoHospital?msg=okDel");
                return;
            }else {
                request.setAttribute("error_borrar", "No es posible eliminar el fichero");
            }
        }catch (Exception e) {
            request.setAttribute("error_borrar", "No es posible eliminar el fichero");
        }
        RequestDispatcher rd = request.getRequestDispatcher("planoHospital.jsp");
        rd.forward(request, response);
    }
}
