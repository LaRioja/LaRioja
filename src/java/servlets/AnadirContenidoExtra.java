/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AnadirContenidoExtra extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("anadirContenidoExtra.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("archivo");
            String fileName = getFileName(filePart);
            InputStream fileContent = filePart.getInputStream();
           /* ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = fileContent.read(buffer)) != -1;) {
                baos.write(buffer, 0, len);
            }

            baos.flush();

            fileContent.close();
            String ruta = "/contenidoExtra";
            String path = request.getRealPath(ruta);

            File fichero = new File(path + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(fichero);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();*/
           
            String url= "http://"+request.getServerName()+":"+request.getServerPort()+request.getServletPath();
            System.out.println(url);
            //sardine.put("", fileContent);

            response.sendRedirect("ContenidoExtra?msg=ok");

        } catch (Exception e) {
            request.setAttribute("contador", 1);
            request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado como contenido extra");
            RequestDispatcher rd = request.getRequestDispatcher("anadirContenidoExtra.jsp");
            rd.forward(request, response);
        }
    }

}
