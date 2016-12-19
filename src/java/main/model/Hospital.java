package main.model;
// Generated 15-dic-2016 13:44:58 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hospital", catalog = "larioja")
public class Hospital implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombremedico", nullable = false, length = 50)
    private String nombremedico;
    
    @Column(name = "apellidomedico", nullable = false, length = 80)
    private String apellidomedico;

    @Column(name = "numeroconsulta", nullable = false)
    private int numeroconsulta;

    @Temporal(TemporalType.TIME)
    @Column(name = "horainicio", nullable = false, length = 8)
    private Date horainicio;

    @Temporal(TemporalType.TIME)
    @Column(name = "horafin", nullable = false, length = 8)
    private Date horafin;

    public Hospital() {
    }

    public Hospital(String nombremedico,String apellidomedico, int numeroconsulta, Date horainicio, Date horafin) {
        this.nombremedico = nombremedico;
        this.apellidomedico = apellidomedico;
        this.numeroconsulta = numeroconsulta;
        this.horainicio = horainicio;
        this.horafin = horafin;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombremedico() {
        return nombremedico;
    }

    public void setNombremedico(String nombremedico) {
        this.nombremedico = nombremedico;
    }

    public String getApellidomedico() {
        return apellidomedico;
    }

    public void setApellidomedico(String apellidomedico) {
        this.apellidomedico = apellidomedico;
    }

    public int getNumeroconsulta() {
        return this.numeroconsulta;
    }

    public void setNumeroconsulta(int numeroconsulta) {
        this.numeroconsulta = numeroconsulta;
    }

    public Date getHorainicio() {
        return this.horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return this.horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

}
