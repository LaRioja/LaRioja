/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageUsuario;
import main.model.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("msg");
        if (param != null ) {
            if(param.compareTo("ok") == 0){
            request.setAttribute("msg", "El usuario ha sido añadido correctamente");
            }else if (param.compareTo("okEdit") == 0){
                request.setAttribute("msg", "El usuario ha sido modificado correctamente");
            }else if (param.compareTo("okDelete") == 0){
                request.setAttribute("msg", "El usuario ha sido eliminado correctamente");
            }else if (param.compareTo("err") == 0){
                request.setAttribute("error", "Error al intentar eliminar el usuario");
            }
        }

        List<Usuario> users = ManageUsuario.list();
        request.setAttribute("users", users);

        RequestDispatcher rd = request.getRequestDispatcher("listaUsuarios.jsp");
        rd.forward(request, response);
    }
}
