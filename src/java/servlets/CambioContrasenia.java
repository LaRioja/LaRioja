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
import javax.servlet.http.HttpSession;
import main.manages.ManageUsuario;
import main.model.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

public class CambioContrasenia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("msg");
        if (param != null && param.compareTo("error") == 0) {
            request.setAttribute("errorpeti", "Petición de contraseña inválida. Es posible que el código de cambio haya expirado. Vuelva a solicitar el cambio");
        }

        RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String pwd = request.getParameter("pwd");
        String rpwd = request.getParameter("rpwd");
        request.setAttribute("pwd", pwd);
        request.setAttribute("rpwd", rpwd);

        if (pwd.compareTo(rpwd) == 0) {
            boolean correcto = true;
            if (!pwd.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40}$")) {
                request.setAttribute("errorpwd", "Las contraseñas no cumplen con la complejidad mínima");
                correcto = false;
            }
            if (correcto) {
                try {
                    HttpSession sesion = request.getSession();
                    Usuario iduser = (Usuario) sesion.getAttribute("user");
                    if (iduser != null) {
                        String contraseniaEncriptada = DigestUtils.shaHex(pwd);
                        iduser.setPassword(contraseniaEncriptada);
                        ManageUsuario.update(iduser);

                        sesion.removeAttribute("user");
                        response.sendRedirect("Inicio?msg=okPass");
                    } else {
                        request.setAttribute("errores", true);
                        request.setAttribute("errorconf", "Error al intentar editar la contraseña. Contacte con el administrador");
                        RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                        rd.forward(request, response);
                    }

                } catch (Exception e) {
                    request.setAttribute("errores", true);
                    request.setAttribute("errorconf", "Error al intentar editar la contraseña. Contacte con el administrador");
                    RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("errores", true);
                RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errores", true);
            request.setAttribute("errorcoincide", "Petición inválida. Es posible que las contraseñas introducidas no coincidan");
            RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
            rd.forward(request, response);
        }
    }

}
