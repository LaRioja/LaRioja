/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContenidoExtra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("msg");
        if (param != null) {
            if (param.compareTo("ok") == 0) {
                request.setAttribute("msg", "El contenido extra ha sido añadido correctamente");
            } else if (param.compareTo("okDel") == 0) {
                request.setAttribute("msg", "El fichero ha sido eliminado correctamente");
            } else if (param.compareTo("okMov") == 0) {
                request.setAttribute("msg", "El fichero ha sido movido correctamente");
            }
        }

        try {
            Sardine sardine = SardineFactory.begin();

            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/activos/";
            List<DavResource> resources = sardine.list(url);
            request.setAttribute("ficheros", resources);
        } catch (Exception e) {
            request.setAttribute("ficheros", new ArrayList<DavResource>());
        }
        RequestDispatcher rd = request.getRequestDispatcher("contenidoExtra.jsp");
        rd.forward(request, response);
    }
}
