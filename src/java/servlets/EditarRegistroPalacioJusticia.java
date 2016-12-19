/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.manages.ManageJusticia;
import main.model.Justicia;

/**
 *
 * @author pmayor
 */
public class EditarRegistroPalacioJusticia extends HttpServlet {

    private Justicia justicia = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        try {
            int identidad = Integer.parseInt(id);
            justicia = ManageJusticia.read(identidad);
            if (justicia != null) {
                request.setAttribute("justicia", justicia);
            } else {
                request.setAttribute("error", "El registro solicitado no existe");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El identificador introducido no es correcto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("editarJusticia.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean error = false;

        if (justicia == null) {
            request.setAttribute("error", "El registro solicitado no existe");
            RequestDispatcher rd = request.getRequestDispatcher("editarJusticia.jsp");
            rd.forward(request, response);
            return;
        }

        String numeroSala = request.getParameter("sala");
        request.setAttribute("sala", numeroSala);
        String procedimiento = request.getParameter("procedimiento");
        request.setAttribute("procedimiento", procedimiento);
        String descripcion = request.getParameter("descripcion");
        request.setAttribute("descripcion", descripcion);
        String fecha = request.getParameter("fecha");

        int sala = 0;
        try {
            sala = Integer.parseInt(numeroSala);
        } catch (NumberFormatException e) {
            request.setAttribute("errorsala", "El formato del número de sala debe ser numérico");
            error = true;
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date fechaProcedimiento = null;
        try {
            fechaProcedimiento = formato.parse(fecha);
            request.setAttribute("fecha", fechaProcedimiento);
        } catch (ParseException ex) {
            request.setAttribute("errorfecha", "La fecha introducida no es correcta");
            error = true;
        }

        if (!error) {
            justicia.setNumerosala(sala);
            justicia.setProcedimiento(procedimiento);
            justicia.setDescripcion(descripcion);
            justicia.setFecha(fechaProcedimiento);
            try {
                ManageJusticia.update(justicia);
                response.sendRedirect("ListaPalacioJusticia?msg=okEdit");
            } catch (Exception e) {
                request.setAttribute("erroredit", "Error al intentar editar el registro");
                RequestDispatcher rd = request.getRequestDispatcher("editarJusticia.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errores", true);
            RequestDispatcher rd = request.getRequestDispatcher("editarJusticia.jsp");
            rd.forward(request, response);
        }
    }

}
