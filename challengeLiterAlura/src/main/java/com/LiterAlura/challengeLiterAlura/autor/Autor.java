package com.LiterAlura.challengeLiterAlura.autor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private String downloads;

    private Integer birthYear;
    private Integer deathYear;

    public Autor() {}

    public Autor(String titulo, String autor, String idioma, String downloads, Integer birthYear, Integer deathYear) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.downloads = downloads;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters e Setters
    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getDownloads() { return downloads; }
    public void setDownloads(String downloads) { this.downloads = downloads; }

}
