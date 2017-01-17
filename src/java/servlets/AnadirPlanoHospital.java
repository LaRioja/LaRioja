/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AnadirPlanoHospital extends HttpServlet {
    
    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("anadirPlanoHospital.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("archivo");
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
            Sardine sardine = SardineFactory.begin();
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/plano/";
            String name = fileName;
            int number = 1;
            while (sardine.exists(url + name)) {
                int posicion = name.lastIndexOf(".");
                if (posicion > 0) {
                    int p = name.lastIndexOf("_");
                    String n = "";
                    if(p!=-1){
                        n = name.substring(p, posicion);
                    }
                    if(n.compareTo("_" + String.valueOf(number-1))==0){
                        name = name.substring(0,p) + "_" + number + name.substring(posicion);
                    }else{
                        name = name.substring(0, posicion) + "_" + number + name.substring(posicion);
                    }                    
                } else {
                    name = name + "_" + number;
                }
                number++;
            }
            sardine.put(url+name, fileContent);
            response.sendRedirect("PlanoHospital?msg=ok");
        }catch (Exception e) {
            request.setAttribute("contador", 1);
            request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado como plano");
            RequestDispatcher rd = request.getRequestDispatcher("anadirPlanoHospital.jsp");
            rd.forward(request, response);
        }
    }
}
