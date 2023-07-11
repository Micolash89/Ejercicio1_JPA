/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JAVIER ESPINDOLA
 */
@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "isbn")
    private Long id;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    @Column(name = "ejemplaresPrestados")
    private Integer prestados;
    @Column(name = "ejemplaresRestantes")
    private Integer restantes;
    private Boolean alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;

    public Long getId() {
        return id;
    }

    public Libro() {
    }

    public Libro(String titulo, Integer anio, Integer ejemplares, Integer prestados, Integer restantes, Boolean alta, Autor autor, Editorial editorial) {
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.prestados = prestados;
        this.restantes = restantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getPrestados() {
        return prestados;
    }

    public void setPrestados(Integer prestados) {
        this.prestados = prestados;
    }

    public Integer getRestantes() {
        return restantes;
    }

    public void setRestantes(Integer restantes) {
        this.restantes = restantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Libro" + "id=" + id + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", prestados=" + prestados + ", restantes=" + restantes + ", alta=" + alta
                + "\nautor" + autor
                + "\neditorial" + editorial;
    }

}
