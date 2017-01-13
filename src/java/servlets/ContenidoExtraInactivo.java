/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContenidoExtraInactivo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("msg");
        if (param != null) {
            if (param.compareTo("ok") == 0) {
                request.setAttribute("msg", "El contenido extra ha sido añadido correctamente");
            }
            if (param.compareTo("okDel") == 0) {
                request.setAttribute("msg", "El fichero ha sido eliminado correctamente");
            }
        }

        String ruta = "/contenidoExtraInactivo";
        String path = request.getRealPath(ruta);

        File f = new File(path);
        if (f.exists() && f.isDirectory()) {
            File[] ficheros = f.listFiles();
            List<String> nombres = new ArrayList<String>();
            for (int i = 0; i < ficheros.length; i++) {
                nombres.add(ficheros[i].getName());

            }
            request.setAttribute("ficheros", ficheros);
        }

        RequestDispatcher rd = request.getRequestDispatcher("contenidoExtraInactivo.jsp");
        rd.forward(request, response);
    }

}
