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
import main.manages.ManageHospital;
import main.model.Hospital;

public class EliminarRegistroHospital extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            Hospital hospital = ManageHospital.read(ident);
            if (hospital != null) {
                ManageHospital.delete(hospital);
                Hospital visitaEmpty = ManageHospital.read(ident);
                if (visitaEmpty == null) {
                    response.sendRedirect("ListaHospital?msg=okDelete");
                } else {
                    response.sendRedirect("ListaHospital?msg=err");
                }
            } else {
                response.sendRedirect("ListaHospital?msg=err");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("ListaHospital?msg=err");
        }
    }
}
