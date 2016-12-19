/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.manages.ManageJusticia;
import main.model.Justicia;

/**
 *
 * @author pmayor
 */
public class ListaPalacioJusticia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Justicia> justicias = ManageJusticia.list();

        String param = request.getParameter("msg");
        if (param != null) {
            if (param.compareTo("ok") == 0) {
                request.setAttribute("msg", "La información ha sido añadida correctamente");
            } else if (param.compareTo("okEdit") == 0) {
                request.setAttribute("msg", "La información ha sido modificada correctamente");
            } else if (param.compareTo("okDel") == 0) {
                request.setAttribute("msg", "La información ha sido eliminada correctamente");
            } else if (param.compareTo("err") == 0) {
                request.setAttribute("error", "Error al intentar eliminar");
            }
        }

        request.setAttribute("justicias", justicias);
        RequestDispatcher rd = request.getRequestDispatcher("justicias.jsp");
        rd.forward(request, response);
    }
}
