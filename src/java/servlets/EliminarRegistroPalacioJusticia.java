/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
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
public class EliminarRegistroPalacioJusticia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            int identidad = Integer.parseInt(id);
            Justicia justicia = ManageJusticia.read(identidad);
            if (justicia != null) {
                try {
                    ManageJusticia.delete(justicia);
                    response.sendRedirect("ListaPalacioJusticia?msg=okDel");
                } catch (Exception e) {
                    response.sendRedirect("ListaPalacioJusticia?msg=err");
                }
            } else {
                response.sendRedirect("ListaPalacioJusticia?msg=err");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("ListaPalacioJusticia?msg=err");
        }
    }
}
