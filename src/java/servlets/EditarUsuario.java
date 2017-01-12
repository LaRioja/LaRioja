/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageUsuario;
import main.manages.ManageRol;
import main.model.Rol;
import main.model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

public class EditarUsuario extends HttpServlet {

    private Usuario user = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("user");
        try {
            int id = Integer.parseInt(name);
            user = ManageUsuario.read(id);
            if (user != null) {
                request.setAttribute("rols", ManageRol.listRol());
                request.setAttribute("usuario", user);
            } else {
                request.setAttribute("error", "El usuario solicitado no existe");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El identificador introducido no es correcto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean error = false;

        if (user == null) {
            request.setAttribute("error", "El usuario solicitado no existe");
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
            return;
        }

        String usuario = request.getParameter("usr");
        request.setAttribute("usr", usuario);
        String contrasenia = request.getParameter("pwd");
        request.setAttribute("pwd", contrasenia);
        String mail = request.getParameter("mail");
        request.setAttribute("mail", mail);
        String rol = request.getParameter("rol");
        request.setAttribute("rolSel", rol);

        request.setAttribute("rols", ManageRol.listRol());

        try {
            if (!contrasenia.isEmpty() && !contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
                request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
                error = true;
            }
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
                error = true;
            }
            if (ManageUsuario.existeNameNotme(usuario, user.getId())) {
                request.setAttribute("errorname", "Ya existe un usuario con ese nombre");
                error = true;
            }
            if (ManageUsuario.existeEmailNotme(mail, user.getId())) {
                request.setAttribute("erroremail", "Ya existe un usuario con ese correo electrónico");
                error = true;
            }
            if (rol == null) {
                request.setAttribute("errorrol", "Debe seleccionar un rol");
                error = true;
            }
            if (!error) {
                user.setUsername(usuario);
                user.setEmail(mail);
                if (!contrasenia.isEmpty()) {
                    String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
                    user.setPassword(contraseniaEncriptada);
                }
                int idRol = Integer.parseInt(rol);
                Rol role = ManageRol.readRol(idRol);
                user.setRol(role);
                ManageUsuario.update(user);
                response.sendRedirect("ListaUsuarios?msg=okEdit");
            } else {
                request.setAttribute("errores", true);
                RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errores", true);
            request.setAttribute("errorconf", "Error al intentar editar el usuario");
            RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
            rd.forward(request, response);
        }
    }

}
