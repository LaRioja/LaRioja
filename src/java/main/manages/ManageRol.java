/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.manages;

import java.util.ArrayList;
import java.util.List;
import main.model.Rol;
import main.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pmayor
 */
public class ManageRol {

    public static List<Rol> listRol() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Rol> rols = new ArrayList();
        try {
            tx = sess.beginTransaction();
            rols = sess.createQuery("from Rol").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return rols;
    }

    public static Rol readRol(int idrol) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Rol rol = new Rol();
        try {
            tx = sess.beginTransaction();
            rol = (Rol) sess.load(Rol.class, idrol);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return rol;
    }
}
