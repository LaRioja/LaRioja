/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.manages.ManageHospital;
import main.model.Hospital;

public class AnadirRegistroHospital extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.setAttribute("error", false);
        RequestDispatcher rd = request.getRequestDispatcher("anadirHospital.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean error = false;
        
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
            Hospital hospital = new Hospital(nombre, apellidos, consulta, horaInicio, horaFin);
            int correcto = ManageHospital.save(hospital);
            if (correcto != -1) {
                response.sendRedirect("ListaHospital?msg=ok");
            } else {
                request.setAttribute("erroradd", "Error al intentar añadir la consulta médica");
                RequestDispatcher rd = request.getRequestDispatcher("anadirHospital.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("error", true);
            RequestDispatcher rd = request.getRequestDispatcher("anadirHospital.jsp");
            rd.forward(request, response);
        }
    }
}
