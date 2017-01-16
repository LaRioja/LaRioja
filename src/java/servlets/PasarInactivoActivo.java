/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasarInactivoActivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String id = request.getParameter("id");
            Sardine sardine = SardineFactory.begin("webdavuser", "password");

            String urlOrigen = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/inactivos/" + id;
            String urlDestino = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/contenidoExtra/activos/";

            int number = 1;
            while (sardine.exists(urlDestino + id)) {
                 int posicion = id.lastIndexOf(".");
                if (posicion > 0) {
                    int p = id.lastIndexOf("_");
                    String n = "";
                    if(p!=-1){
                        n = id.substring(p, posicion);
                    }
                    if(n.compareTo("_" + String.valueOf(number-1))==0){
                        id = id.substring(0,p) + "_" + number + id.substring(posicion);
                    }else{
                        id = id.substring(0, posicion) + "_" + number + id.substring(posicion);
                    }                    
                } else {
                    id = id + "_" + number;
                }
                number++;
            }

            sardine.move(urlOrigen, urlDestino + id);
            response.sendRedirect("ContenidoExtra?msg=okMov");

        } catch (Exception e) {
            request.setAttribute("error_pasar", "No es posible pasar el fichero de inactivo a activo");
            RequestDispatcher rd = request.getRequestDispatcher("contenidoExtraInactivo.jsp");
            rd.forward(request, response);
        }
    }
}
