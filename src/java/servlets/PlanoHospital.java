
package servlets;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanoHospital extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String param = request.getParameter("msg");
        if (param != null) {
            if (param.compareTo("ok") == 0) {
                request.setAttribute("msg", "El plano ha sido añadido correctamente");
            } else if (param.compareTo("okDel") == 0) {
                request.setAttribute("msg", "El plano ha sido eliminado correctamente");
            }
        }
        
        try {
            Sardine sardine = SardineFactory.begin();
            String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath() + "/contenidos/plano/";
            List<DavResource> resources = sardine.list(url);
            request.setAttribute("ficheros", resources);
        }catch (Exception e) {
            request.setAttribute("ficheros", new ArrayList<DavResource>());
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("planoHospital.jsp");
        rd.forward(request, response);
    }
}
