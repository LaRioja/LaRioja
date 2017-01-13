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
            Sardine sardine = SardineFactory.begin("webdavuser", "password");
            String url= "http://"+request.getServerName()+":"+request.getServerPort()+request.getServletContext().getContextPath()+"/contenidos/contenidoExtra/"+fileName;
            sardine.put(url, fileContent);

            response.sendRedirect("ContenidoExtra?msg=ok");

        } catch (IOException | ServletException e) {
            request.setAttribute("contador", 1);
            request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado como contenido extra");
            RequestDispatcher rd = request.getRequestDispatcher("anadirContenidoExtra.jsp");
            rd.forward(request, response);
        }
    }

}
