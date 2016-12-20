/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageUsuario;
import main.model.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("user");
        try {
            int id = Integer.parseInt(name);
            Usuario user = ManageUsuario.read(id);
            if (user != null) {
                ManageUsuario.delete(user);
                response.sendRedirect("ListaUsuarios?msg=okDelete");
            } else {
                response.sendRedirect("ListaUsuarios?msg=err");
            }
        } catch (Exception e) {
            response.sendRedirect("ListaUsuarios?msg=err");
        }
    }
}
