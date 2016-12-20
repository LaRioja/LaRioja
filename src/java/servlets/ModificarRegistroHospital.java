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
import main.manages.ManageHospital;
import main.model.Hospital;

public class ModificarRegistroHospital extends HttpServlet {

    private Hospital hospital = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            int ident = Integer.parseInt(id);
            hospital = ManageHospital.read(ident);
            if (hospital != null) {
                request.setAttribute("hospital", hospital);
            } else {
                request.setAttribute("error", "El registro solicitado no existe");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El identificador introducido no es correcto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("editarHospital.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean error = false;

        if (hospital == null) {
            request.setAttribute("error", "El registro solicitado no existe");
            RequestDispatcher rd = request.getRequestDispatcher("editarHospital.jsp");
            rd.forward(request, response);
            return;
        }

        String nombre = request.getParameter("nombre");
        request.setAttribute("nombre", nombre);
        String apellidos = request.getParameter("apellidos");
        request.setAttribute("apellidos", apellidos);
        String numConsulta = request.getParameter("consulta");
        request.setAttribute("consulta", numConsulta);
        String horaIni = request.getParameter("horaIni");
        String horaF = request.getParameter("horaFin");

        int consulta = 0;
        try {
            consulta = Integer.parseInt(numConsulta);
        } catch (NumberFormatException e) {
            request.setAttribute("errorconsulta", "El formato del número de consulta debe ser numérico");
            error = true;
        }

        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        Date horaInicio = null;
        try {
            horaInicio = formato.parse(horaIni);
            request.setAttribute("horaIni", horaInicio);
        } catch (ParseException ex) {
            request.setAttribute("errorhorai", "La hora de inicio introducida no es correcta");
            error = true;
        }

        Date horaFin = null;
        try {
            horaFin = formato.parse(horaF);
            request.setAttribute("horaFin", horaFin);
        } catch (ParseException ex) {
            request.setAttribute("errorhoraf", "La hora de fin introducida no es correcta");
            error = true;
        }

        if (!error) {
            hospital.setNombremedico(nombre);
            hospital.setApellidomedico(apellidos);
            hospital.setNumeroconsulta(consulta);
            hospital.setHorainicio(horaInicio);
            hospital.setHorafin(horaFin);

            try {
                ManageHospital.update(hospital);
                response.sendRedirect("ListaHospital?msg=okEdit");
            } catch (Exception e) {
                request.setAttribute("erroredit", "Error al intentar editar el registro");
                RequestDispatcher rd = request.getRequestDispatcher("editarHospital.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errores", true);
            RequestDispatcher rd = request.getRequestDispatcher("editarHospital.jsp");
            rd.forward(request, response);
        }
    }
}
