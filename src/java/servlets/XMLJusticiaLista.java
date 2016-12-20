/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import main.manages.ManageJusticia;
import main.model.Justicia;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author pmayor
 */
public class XMLJusticiaLista extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");

        try {
            List<Justicia> justicias = ManageJusticia.listOrdenada();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = (Element) document.createElement("justicia");
            document.appendChild(root);

            for (Justicia justicia : justicias) {
                Element item = (Element) document.createElement("item");
                root.appendChild(item);

                Element sala = (Element) document.createElement("sala");
                item.appendChild(sala);
                String textSala = String.valueOf(justicia.getNumerosala());
                sala.appendChild(document.createTextNode(textSala));

                Element procedimiento = (Element) document.createElement("procedimiento");
                item.appendChild(procedimiento);
                procedimiento.appendChild(document.createTextNode(justicia.getProcedimiento()));
                
                Element descripcion = (Element) document.createElement("descripcion");
                item.appendChild(descripcion);
                descripcion.appendChild(document.createTextNode(justicia.getDescripcion()));
                
                Element fecha = (Element) document.createElement("fecha");
                item.appendChild(fecha);
                SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
                fecha.appendChild(document.createTextNode(formato.format(justicia.getFecha())));
            }

            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputByteStream(response.getOutputStream());
            serializer.serialize(document);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLJusticiaLista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
