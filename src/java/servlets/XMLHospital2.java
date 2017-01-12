/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import main.manages.ManageHospital;
import main.manages.ManagePlano;
import main.model.Hospital;
import main.model.Plano;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLHospital2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");

        try {

            List<Hospital> hospitales = ManageHospital.list();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document document = null;

            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();

            String ip = InetAddress.getLocalHost().getHostAddress();
            int puerto = request.getServerPort();
            String rutaPlano = "/plano/";
            String path = request.getContextPath();

            Element root = (Element) document.createElement("ConsultaMedica");
            document.appendChild(root);

            Plano plano = ManagePlano.first();

            Element p = (Element) document.createElement("plano");
            root.appendChild(p);
            if (plano == null) {
                p.appendChild(document.createTextNode(""));
            } else {
                p.appendChild(document.createTextNode("http://" + ip + ":" + puerto + path + rutaPlano + plano.getNombre()));
            }

            for (Hospital h : hospitales) {
                Element item = (Element) document.createElement("item");
                root.appendChild(item);

                Element nombre = (Element) document.createElement("nombre");
                item.appendChild(nombre);
                nombre.appendChild(document.createTextNode(h.getApellidomedico() + ", " + h.getNombremedico()));

                Element consulta = (Element) document.createElement("consulta");
                item.appendChild(consulta);
                consulta.appendChild(document.createTextNode(h.getNumeroconsulta() + ""));

                Element horas = (Element) document.createElement("horas");
                item.appendChild(horas);
                SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
                horas.appendChild(document.createTextNode(formato.format(h.getHorainicio()) + " - " + formato.format(h.getHorafin())));

            }

            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(document);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLHospital2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
