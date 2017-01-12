/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.manages;

import main.model.Recuerdocontrasenia;
import main.model.Usuario;
import main.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pmayor
 */
public class ManageContrasenia {

    public static int save(Recuerdocontrasenia contra) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(contra);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return ok;
    }

    public static Usuario existeCodigo(String codigo) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Usuario user = null;
        try {
            tx = sess.beginTransaction();
            user = (Usuario) sess.createQuery("select usuario from Recuerdocontrasenia where codigo = :cod")
                    .setParameter("cod", codigo).uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return user;
    }

    public static void delete() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.createSQLQuery("DELETE FROM recuerdocontrasenia WHERE fecha < DATE_ADD(now(), INTERVAL '-30' MINUTE)").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            sess.close();
        }
    }
}
