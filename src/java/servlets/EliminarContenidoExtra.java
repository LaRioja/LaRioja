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

public class EliminarContenidoExtra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        
        String ruta = "/contenidoExtra";
        String path = request.getRealPath(ruta);
        boolean borrado = false;
        boolean encontrado = false;

        File f = new File(path);
        if (f.exists() && f.isDirectory()) {
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                if(ficheros[i].getName().compareTo(id)==0){
                    borrado = ficheros[i].delete();
                    encontrado = true;
                }
            }
        }
        
        if(encontrado && borrado){
            response.sendRedirect("ContenidoExtra?msg=okDel");
        }else if(!borrado){
            request.setAttribute("error_borrar", "No es posible eliminar el fichero");
            RequestDispatcher rd = request.getRequestDispatcher("contenidoExtra.jsp");
            rd.forward(request, response);
        }
    }

}
