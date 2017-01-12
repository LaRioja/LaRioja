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
import javax.servlet.http.HttpSession;
import main.manages.ManageContrasenia;
import main.model.Usuario;

public class PideNuevaContrasenia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cod = request.getParameter("cc");
        Usuario user = ManageContrasenia.existeCodigo(cod);
        if (user == null) {
            response.sendRedirect("CambioContrasenia?msg=error");
        } else {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("user", user);
            response.sendRedirect("CambioContrasenia");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
