package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Pokemon")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Nacemento")
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PokedexEntry", referencedColumnName = "id")
    private Pokedex pokedexEntry;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Adestrador", referencedColumnName = "id")
    private Adestrador adestrador;

    public Pokemon(){}

    public Pokemon(String nombre, Date fecha, Pokedex pokedexEntry, Adestrador adestrador) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.pokedexEntry = pokedexEntry;
        this.adestrador = adestrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pokedex getPokedexEntry() {
        return pokedexEntry;
    }

    public void setPokedexEntry(Pokedex pokedexEntry) {
        this.pokedexEntry = pokedexEntry;
    }

    public Adestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Adestrador adestrador) {
        this.adestrador = adestrador;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", pokedexEntry=" + pokedexEntry +
                ", adestrador=" + adestrador +
                '}';
    }
}
