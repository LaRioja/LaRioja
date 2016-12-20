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
                    Usuario iduser = (Usuario)sesion.getAttribute("iduser");
                    if (iduser != null) {
                        String contraseniaEncriptada = DigestUtils.shaHex(pwd);
                        iduser.setPassword(contraseniaEncriptada);
                        ManageUsuario.update(iduser);

                        sesion.removeAttribute("iduser");
                        response.sendRedirect("Inicio?msg=okCambio");
                    } else {
                        request.setAttribute("errorconf", "Error al intentar editar la contraseña");
                        RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                        rd.forward(request, response);
                    }

                } catch (Exception e) {
                    request.setAttribute("errorconf", "Error al intentar editar la contraseña");
                    RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errorcoincide", "Petición inválida. Es posible que las contraseñas introducidas no coincidan");
            RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.jsp");
            rd.forward(request, response);
        }
    }

}
