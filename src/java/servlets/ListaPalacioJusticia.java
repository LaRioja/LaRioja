/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ListaPalacioJusticia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Justicia> justicias = ManageJusticia.listToday();

        String param = request.getParameter("msg");
        if (param != null) {
            if (param.compareTo("ok") == 0) {
                request.setAttribute("msg", "La información ha sido añadida correctamente");
            } else if (param.compareTo("okEdit") == 0) {
                request.setAttribute("msg", "La información ha sido modificada correctamente");
            } else if (param.compareTo("okDel") == 0) {
                request.setAttribute("msg", "La información ha sido eliminada correctamente");
            } else if (param.compareTo("err") == 0) {
                request.setAttribute("error", "Error al intentar eliminar");
            } else if (param.compareTo("okConf") == 0) {
                request.setAttribute("msg", "La configuración de la cuenta ha sido correctamente editada");
            }
        }

        request.setAttribute("justicias", justicias);
        RequestDispatcher rd = request.getRequestDispatcher("justicias.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fechaInicio = request.getParameter("fechaIni");
        String fechaFin = request.getParameter("fechaFin");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaI = null;
        Date fechaF = null;

        try {
            if (fechaInicio != null) {
                fechaI = formato.parse(fechaInicio);
                request.setAttribute("fechaIni", fechaInicio);
            }
            if (fechaFin != null) {
                fechaF = formato.parse(fechaFin);
                System.out.println("32154854");
                request.setAttribute("fechaFin", fechaFin);
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        List<Justicia> justicias = new ArrayList<Justicia>();
        if (fechaI != null && fechaF != null) {
            justicias = ManageJusticia.listInterval(fechaI, fechaF);
        } else if (fechaI != null) {
            justicias = ManageJusticia.listOneDate(fechaI);
            System.err.println("////");
        } else if (fechaF != null) {
            System.err.println("-**");
            justicias = ManageJusticia.listOneDate(fechaF);
        } else {
            System.err.println("222");
            justicias = ManageJusticia.listToday();
        }

        request.setAttribute("justicias", justicias);
        RequestDispatcher rd = request.getRequestDispatcher("justicias.jsp");
        rd.forward(request, response);
    }
}
