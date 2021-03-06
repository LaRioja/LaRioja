package main.model;
// Generated 15-dic-2016 13:44:58 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name = "rol", catalog = "larioja", uniqueConstraints = @UniqueConstraint(columnNames = "rolname"))
public class Rol implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "rolname", unique = true, nullable = false, length = 30)
    private String rolname;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public Rol() {
    }

    public Rol(String rolname) {
        this.rolname = rolname;
    }

    public Rol(String rolname, Set<Usuario> usuarios) {
        this.rolname = rolname;
        this.usuarios = usuarios;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolname() {
        return this.rolname;
    }

    public void setRolname(String rolname) {
        this.rolname = rolname;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
