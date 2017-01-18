/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.manages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import main.model.Justicia;
import main.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pmayor
 */
public class ManageJusticia {

    public static int save(Justicia justicia) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Integer ok = -1;
        try {
            tx = sess.beginTransaction();
            ok = (Integer) sess.save(justicia);
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

    public static void update(Justicia justicia) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.merge(justicia);
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

    public static void delete(Justicia justicia) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            sess.delete(justicia);
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

    public static List<Justicia> list() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            justicia = sess.createQuery("from Justicia").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static List<Justicia> listToday() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            justicia = sess.createQuery("from Justicia j where year(j.fecha) = :anio and month(j.fecha)= :mes and day(j.fecha) = :dia")
                    .setParameter("anio", anio)
                    .setParameter("mes", mes)
                    .setParameter("dia", dia)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static List<Justicia> listOrdenada() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            justicia = sess.createQuery("from Justicia j where year(j.fecha) = :anio and month(j.fecha)= :mes and day(j.fecha) = :dia ORDER BY j.fecha")
                    .setParameter("anio", anio)
                    .setParameter("mes", mes)
                    .setParameter("dia", dia)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static List<Justicia> listSalaOrdenada(int sala) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            Calendar cal = Calendar.getInstance();
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            justicia = sess.createQuery("from Justicia j where year(j.fecha) = :anio and month(j.fecha)= :mes and day(j.fecha) = :dia and j.numerosala = :sala ORDER BY j.fecha")
                    .setParameter("anio", anio)
                    .setParameter("mes", mes)
                    .setParameter("dia", dia)
                    .setParameter("sala", sala)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static List<Justicia> listOneDate(Date fecha) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int anio = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            justicia = sess.createQuery("from Justicia j where year(j.fecha) = :anio and month(j.fecha)= :mes and day(j.fecha) = :dia")
                    .setParameter("anio", anio)
                    .setParameter("mes", mes)
                    .setParameter("dia", dia)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static List<Justicia> listInterval(Date fechaI, Date fechaF) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        List<Justicia> justicia = new ArrayList();
        try {
            tx = sess.beginTransaction();
            justicia = sess.createQuery("from Justicia j where cast(j.fecha as date) BETWEEN :startDate AND :endDate")
                    .setParameter("startDate", fechaI)
                    .setParameter("endDate", fechaF)
                    .list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }

    public static Justicia read(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session sess = factory.openSession();
        Transaction tx = null;
        Justicia justicia = new Justicia();
        try {
            tx = sess.beginTransaction();
            justicia = (Justicia) sess.get(Justicia.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sess.close();
        }
        return justicia;
    }
}
