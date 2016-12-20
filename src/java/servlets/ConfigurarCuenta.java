/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageUsuario;
import main.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class ConfigurarCuenta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession misession = (HttpSession) request.getSession();
        String usuario=(String) misession.getAttribute("username");
        List<Usuario> users=new ArrayList<Usuario>();
        if (usuario == null) {
            users = ManageUsuario.listOneUser(request.getUserPrincipal().getName());
        }else{
            users = ManageUsuario.listOneUser(usuario);
        }
        if (users == null || users.isEmpty()) {
            request.setAttribute("error", "No es posible configurar la cuenta");
            RequestDispatcher rd = request.getRequestDispatcher("configurarCuenta.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("usuario", users.get(0));
            RequestDispatcher rd = request.getRequestDispatcher("configurarCuenta.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usr");
        request.setAttribute("usr", usuario);
        String contrasenia = request.getParameter("pwd");
        request.setAttribute("pwd", contrasenia);
        String mail = request.getParameter("mail");
        request.setAttribute("mail", mail);
        String id = request.getParameter("id");
        request.setAttribute("id", id);

        try {
            int ident = Integer.parseInt(id);

            boolean correcto = true;

            if (!contrasenia.isEmpty() && !contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
                request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
                correcto = false;
            }
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
                correcto = false;
            }
            if (ManageUsuario.existeNameNotme(usuario, ident)) {
                request.setAttribute("errorname", "Ya existe un usuario con ese nombre");
                correcto = false;
            }
            if (ManageUsuario.existeEmailNotme(mail, ident)) {
                request.setAttribute("erroremail", "Ya existe un usuario con ese correo electrónico");
                correcto = false;
            }
            if (correcto) {

                Usuario user = ManageUsuario.read(ident);
                if (user != null) {
                    user.setUsername(usuario);
                    user.setEmail(mail);
                    if (!contrasenia.isEmpty()) {
                        String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
                        user.setPassword(contraseniaEncriptada);
                    }
                    ManageUsuario.update(user);
                    
                    HttpSession misession = (HttpSession) request.getSession();
                    misession.setAttribute("username", usuario);
                    
                    response.sendRedirect("Inicio?msg=ok");
                } else {
                    request.setAttribute("errorconf", "Error al intentar editar la cuenta");
                    RequestDispatcher rd = request.getRequestDispatcher("configurarCuenta.jsp");
                    rd.forward(request, response);
                }

            } else {
                RequestDispatcher rd = request.getRequestDispatcher("configurarCuenta.jsp");
                rd.forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorconf", "Error al intentar editar la cuenta");
            RequestDispatcher rd = request.getRequestDispatcher("configurarCuenta.jsp");
            rd.forward(request, response);
        }
    }

}
