package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Pokedex")
public class Pokedex implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "Peso", precision = 10,scale = 2)
    private BigDecimal peso;

    @Column(name = "Misc")
    private String misc;

    public Pokedex() {}

    public Pokedex(String nome, BigDecimal peso, String misc) {
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

}
