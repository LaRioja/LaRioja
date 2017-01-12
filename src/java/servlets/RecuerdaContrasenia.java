/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import main.manages.ManageContrasenia;
import main.manages.ManageUsuario;
import main.model.Recuerdocontrasenia;
import main.model.Usuario;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class RecuerdaContrasenia extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("errores", false);
        RequestDispatcher rd = request.getRequestDispatcher("recuerdoContrasenia.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        Captcha captcha = (Captcha) sesion.getAttribute(Captcha.NAME);
        String txtCaptcha = request.getParameter("txtCaptcha");
        String nom_usu = request.getParameter("usr");
        request.setAttribute("user", nom_usu);
        boolean correcto = true;
        Usuario usuario = ManageUsuario.listOneUser(nom_usu);
        if (!captcha.isCorrect(txtCaptcha)) {
            request.setAttribute("errorcaptcha", "El texto introducido no corresponde con el texto de la imagen");
            correcto = false;
        }
        if (usuario == null) {
            request.setAttribute("erroruser", "El usuario requerido no existe");
            correcto = false;
        }
        if (correcto) {
            String cod = java.util.UUID.randomUUID().toString();
            Date fecha = new Date();
            Recuerdocontrasenia recuerdo = new Recuerdocontrasenia(usuario, cod, new Timestamp(fecha.getTime()));

            int ok = ManageContrasenia.save(recuerdo);
            if (ok != -1) {
                String urreles = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/PideNuevaContrasenia?cc=" + cod;
                try {
                    Properties props = new PropertiesMail().getProperties();

                    Session session = Session.getDefaultInstance(props);
                    session.setDebug(true);

                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(props.getProperty("mail.smtp.user")));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail()));
                    message.setSubject("Cambio contrasenia");
                    message.setText("Usa el siguiente enlace para acceder a una página donde podrás cambiar tu contraseña: \n" + urreles,
                            "UTF-8",
                            "html");

                    Transport t = session.getTransport("smtp");
                    t.connect(props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
                    t.sendMessage(message, message.getAllRecipients());
                    t.close();

                    response.sendRedirect(request.getContextPath() + "/Inicio");
                } catch (MessagingException e) {
                    request.setAttribute("errores", true);
                    request.setAttribute("errormail", "No es posible enviar un correo al usuario solicitado");
                    RequestDispatcher rd = request.getRequestDispatcher("recuerdoContrasenia.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("errores", true);
                request.setAttribute("errorpass", "No es posible recuperar la contraseña del usuario solicitado. Inténtelo de nuevo");
                RequestDispatcher rd = request.getRequestDispatcher("recuerdoContrasenia.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("errores", true);
            RequestDispatcher rd = request.getRequestDispatcher("recuerdoContrasenia.jsp");
            rd.forward(request, response);
        }
    }

}
