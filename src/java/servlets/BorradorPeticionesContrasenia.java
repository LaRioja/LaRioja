/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import main.manages.ManageContrasenia;

public class BorradorPeticionesContrasenia implements ServletContextListener {

    private ScheduledExecutorService planificador;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        planificador = Executors.newSingleThreadScheduledExecutor();
        planificador.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try{
                    ManageContrasenia.delete();
                    Logger.getLogger(BorradorPeticionesContrasenia.class.getName()).log(Level.SEVERE, "Se está ejecutando el borrado de las peticiones obsoletas");
                }catch (Exception e){
                    Logger.getLogger(BorradorPeticionesContrasenia.class.getName()).log(Level.SEVERE, "Error durante la ejecución del borrado de peticiones obsoletas");
                }
            }
        }, 0, 30, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        planificador.shutdownNow();
    }
}
