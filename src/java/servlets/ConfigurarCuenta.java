/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageUsuario;
import main.model.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

public class ConfigurarCuenta extends HttpServlet {

    private Usuario user = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession misession = (HttpSession) request.getSession();
        String username = (String) misession.getAttribute("username");

        String url = request.getRequestURI();
        String param = "";
        if (url.contains("Hospital")) {
            param = "Hospital";
        } else if (url.contains("Justicia")) {
            param = "Justicia";
        } else if (url.contains("Admin")) {
            param = "Admin";
        }

        if (username == null) {
            user = ManageUsuario.listOneUser(request.getUserPrincipal().getName());
        } else {
            user = ManageUsuario.listOneUser(username);
        }

        if (user == null) {
            request.setAttribute("error", "No es posible configurar la cuenta");
            RequestDispatcher rd = request.getRequestDispatcher("../configuracionCuenta.jsp?origen=" + param);
            rd.forward(request, response);
        } else {
            request.setAttribute("usuario", user);
            RequestDispatcher rd = request.getRequestDispatcher("../configuracionCuenta.jsp?origen=" + param);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        boolean correcto = true;

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

        try {
            if (!contrasenia.isEmpty() && !contrasenia.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
                request.setAttribute("errorpwd", "La contraseña no cumple con la complejidad mínima");
                correcto = false;
            }
            if (!mail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                request.setAttribute("errormail", "El correo electrónico no es válido");
                correcto = false;
            }
            if (ManageUsuario.existeNameNotme(usuario, user.getId())) {
                request.setAttribute("errorname", "Ya existe un usuario con ese nombre");
                correcto = false;
            }
            if (ManageUsuario.existeEmailNotme(mail, user.getId())) {
                request.setAttribute("erroremail", "Ya existe un usuario con ese correo electrónico");
                correcto = false;
            }

            String url = request.getRequestURI();
            String param = "";

            if (correcto) {
                user.setUsername(usuario);
                user.setEmail(mail);
                if (!contrasenia.isEmpty()) {
                    String contraseniaEncriptada = DigestUtils.shaHex(contrasenia);
                    user.setPassword(contraseniaEncriptada);
                }
                ManageUsuario.update(user);

                HttpSession misession = (HttpSession) request.getSession();
                misession.setAttribute("username", usuario);

                if (url.contains("Hospital")) {
                    response.sendRedirect("ListaHospital?msg=okConf");
                } else if (url.contains("Justicia")) {
                    response.sendRedirect("ListaPalacioJusticia?msg=okConf");
                } else if (url.contains("Admin")) {
                    response.sendRedirect("ListaUsuarios?msg=okConf");
                }
            } else {
                request.setAttribute("errores", true);
                if (url.contains("Hospital")) {
                    RequestDispatcher rd = request.getRequestDispatcher("../configuracionCuenta.jsp?origen=" + "Hospital");
                    rd.forward(request, response);
                } else if (url.contains("Justicia")) {
                    RequestDispatcher rd = request.getRequestDispatcher("../configuracionCuenta.jsp?origen=" + "Justicia");
                    rd.forward(request, response);
                } else if (url.contains("Admin")) {
                    RequestDispatcher rd = request.getRequestDispatcher("../configuracionCuenta.jsp?origen=" + "Admin");
                    rd.forward(request, response);
                }
            }
        } catch (Exception e) {
            request.setAttribute("errores", true);
            request.setAttribute("errorconf", "Error al intentar editar la cuenta");
            RequestDispatcher rd = request.getRequestDispatcher("configuracionCuenta.jsp");
            rd.forward(request, response);
        }
    }

}
