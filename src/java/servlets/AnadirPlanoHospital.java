/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.manages.ManageHospital;
import main.manages.ManagePlano;
import main.model.Plano;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AnadirPlanoHospital extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        request.setAttribute("plano", ManagePlano.first().getNombre());
        RequestDispatcher rd = request.getRequestDispatcher("planoHospital.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        boolean error = false;

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                
                FileItem plano = null;
                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    String campoN = item.getFieldName();
                    if (campoN.equals("plano") && item.getName() != null && item.getName().trim().length() > 0) {
                        plano = item;
                    }
                }

                if (plano != null) {
                    int i = plano.getName().lastIndexOf(".");
                    String ex = "";
                    if (i != -1) {
                        ex = plano.getName().substring(i + 1);
                    }
                    if (ex.compareToIgnoreCase("jpg") != 0 && ex.compareToIgnoreCase("png") != 0
                            && ex.compareToIgnoreCase("jpeg") != 0 && ex.compareToIgnoreCase("bmp") != 0
                            && ex.compareToIgnoreCase("gif") != 0 && ex.compareToIgnoreCase("tiff") != 0) {
                        error = true;
                        request.setAttribute("error_foto", "El fichero seleccionado para el plano del hospital no es una imagen");
                    }
                }
                if (error) {
                    //request.setAttribute("error", error);
                    //request.setAttribute("hospitales", ManageHospital.list());

                    request.setAttribute("plano", ManagePlano.first().getNombre());

                    RequestDispatcher rd = request.getRequestDispatcher("planoHospital.jsp");
                    rd.forward(request, response);
                } else if (plano != null) {
                    String ruta = "/plano";
                    String path = request.getRealPath(ruta);
                    String nom = "";
                    String nomF = "";

                    nom += plano.getName();
                    String auxN = nom;
                    File f = new File(path + "/" + nom);

                    int ex = 0;
                    while (f.exists()) {
                        ex++;
                        StringTokenizer st = new StringTokenizer(auxN, ".");
                        List<String> n = new ArrayList<String>();
                        String aux;
                        while (st.hasMoreTokens()) {
                            aux = st.nextToken();
                            n.add(aux);
                        }
                        String nombreFoto = n.get(0);
                        String extension = n.get(1);

                        nomF = nombreFoto + ex;
                        String no = "";
                        no = nomF + "." + extension;

                        f = new File(path + "/" + no);
                        nom = no;
                    }
                    
                    plano.write(f);

                    Plano plan = new Plano(nom);

                    Plano planoBD = ManagePlano.first();
                    int estado = ManagePlano.save(plan);

                    if (estado != -1) {
                        if (planoBD != null) {
                            File f1 = new File(path + "/" + planoBD.getNombre());
                            f1.delete();
                            ManagePlano.delete(planoBD);
                        }
                        response.sendRedirect("ListaHospital");
                    } else {
                        request.setAttribute("contador", 1);
                        request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el plano del hospital");
                        RequestDispatcher rd = request.getRequestDispatcher("hospital.jsp");
                        rd.forward(request, response);
                    }
                }

            } catch (FileUploadException ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el plano del hospital");
                RequestDispatcher rd = request.getRequestDispatcher("hopital.jsp");
                rd.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("contador", 1);
                request.setAttribute("error_foto", "No es posible añadir el fichero seleccionado para el plano de hospital");
                RequestDispatcher rd = request.getRequestDispatcher("hospital.jsp");
                rd.forward(request, response);
            }
        }

    }
}
