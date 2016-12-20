/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.manages.ManageRol;
import main.manages.ManageUsuario;
import main.model.Rol;
import main.model.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

public class AnadirUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean error = false;

        String usuario = request.getParameter("usr");
        request.setAttribute("usr", usuario);
        String contrasenia = request.getParameter("pwd");
        request.setAttribute("pwd", contrasenia);
        String mail = request.getParameter("mail");
        request.setAttribute("mail", mail);
        String rol = request.getParameter("rol");
        request.setAttribute("rol", rol);
        request.setAttribute("rols", ManageRol.listRol());

        if (!contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
            request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
            error = true;
        }
        if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            request.setAttribute("errormail", "El correo electrónico no es válido");
            error = true;
        }
        if (ManageUsuario.existeName(usuario)) {
            request.setAttribute("errorname", "Ya existe un usuario con ese nombre");
            error = true;
        }
        if (ManageUsuario.existeEmail(mail)) {
            request.setAttribute("erroremail", "Ya existe un usuario con ese correo electrónico");
            error = true;
        }

        if (!error) {
            String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
            int id = Integer.parseInt(rol);
            Rol role = ManageRol.readRol(id);
            Usuario user = new Usuario(usuario, contraseniaEncriptada, mail, role);
            int ok = ManageUsuario.save(user);
            
            if (ok != -1) {
                response.sendRedirect("ListaUsuarios?msg=ok");
            } else {
                request.setAttribute("error", "Error al intentar añadir el usuario");
                RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errores", true);
            RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("errores", false);
        request.setAttribute("rols", ManageRol.listRol());

        RequestDispatcher rd = request.getRequestDispatcher("anadirUsuario.jsp");
        rd.forward(request, response);
    }
}
