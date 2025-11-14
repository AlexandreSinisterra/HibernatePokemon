package app;

import model.Adestrador;
import model.Pokedex;
import services.AdestradorService;
import services.PokedexService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PokedexService pokedexService = new PokedexService();
        AdestradorService adestradorService = new AdestradorService();

        System.out.println("insertando los 10 pokemons");

        Pokedex pokedex1  = new Pokedex("Bulbasaur",   new BigDecimal("6.90"),  "Un Pokémon planta que almacena energía en su bulbo.");
        Pokedex pokedex2  = new Pokedex("Charmander",  new BigDecimal("8.50"),  "La llama de su cola indica su fuerza vital.");
        Pokedex pokedex3  = new Pokedex("Squirtle",    new BigDecimal("9.00"),  "Dispara agua a presión desde su boca.");
        Pokedex pokedex4  = new Pokedex("Pikachu",     new BigDecimal("6.00"),  "Acumula electricidad en sus mejillas.");
        Pokedex pokedex5  = new Pokedex("Jigglypuff",  new BigDecimal("5.50"),  "Duerme a sus enemigos con su canto.");
        Pokedex pokedex6  = new Pokedex("Meowth",      new BigDecimal("4.20"),  "Le encanta el dinero y las cosas brillantes.");
        Pokedex pokedex7  = new Pokedex("Psyduck",     new BigDecimal("19.60"), "Sufre dolores de cabeza que liberan poder psíquico.");
        Pokedex pokedex8  = new Pokedex("Growlithe",   new BigDecimal("19.00"), "Fiel y valiente, siempre protege a su entrenador.");
        Pokedex pokedex9  = new Pokedex("Geodude",     new BigDecimal("20.00"), "Un Pokémon roca que vive entre montañas.");
        Pokedex pokedex10 = new Pokedex("Eevee",       new BigDecimal("6.50"),  "Código genético inestable que permite varias evoluciones.");

        pokedexService.insertarPokedex(pokedex1);
        pokedexService.insertarPokedex(pokedex2);
        pokedexService.insertarPokedex(pokedex3);
        pokedexService.insertarPokedex(pokedex4);
        pokedexService.insertarPokedex(pokedex5);
        pokedexService.insertarPokedex(pokedex6);
        pokedexService.insertarPokedex(pokedex7);
        pokedexService.insertarPokedex(pokedex8);
        pokedexService.insertarPokedex(pokedex9);
        pokedexService.insertarPokedex(pokedex10);

        System.out.println("Insertando adestradores");

        Adestrador adestrador1 = new Adestrador("Red", Date.valueOf("2006-03-22"));
        Adestrador adestrador2 = new Adestrador("N", Date.valueOf("2006-02-11"));

        adestradorService.insertarAdestrador(adestrador1);
        adestradorService.insertarAdestrador(adestrador2);

        System.out.println("listando pokedex");

        List<Pokedex> lista = pokedexService.listarPokedex();
        for (Pokedex p: lista){
            System.out.println(p);
        }

        System.out.println("listando adestradores");

        List<Adestrador> listaAdestrador = adestradorService.listarAdestradores();
        for (Adestrador a: listaAdestrador){
            System.out.println(a);
        }

        System.out.println("modificando 2 adestradores");

        Adestrador adestradorACambiar1 = adestradorService.obtenerPorIdAdestrador(1);
        Adestrador adestradorACambiar2 = adestradorService.obtenerPorIdAdestrador(2);
        adestradorACambiar1.setFecha(Date.valueOf("2001-11-09"));
        adestradorACambiar2.setNombre("n");
        adestradorService.updateAdestrador(adestradorACambiar1);
        adestradorService.updateAdestrador(adestradorACambiar2);

        System.out.println("modificando 2 entradas de la pokedex");

        Pokedex pokemonACambiar1 = pokedexService.obternerPorIdPokedex(1);
        Pokedex pokemonACambiar2 = pokedexService.obternerPorIdPokedex(2);
        pokemonACambiar1.setPeso(new BigDecimal("7.00"));
        pokemonACambiar2.setPeso(new BigDecimal("9.00"));
        pokedexService.actualizarPokedex(pokemonACambiar1);
        pokedexService.actualizarPokedex(pokemonACambiar2);

        System.out.println("listando pokedex2");

        List<Pokedex> lista2 = pokedexService.listarPokedex();
        for (Pokedex p: lista2){
            System.out.println(p);
        }

        System.out.println("listando adestradores2");

        List<Adestrador> listaAdestrador2 = adestradorService.listarAdestradores();
        for (Adestrador a: listaAdestrador2){
            System.out.println(a);
        }

        System.out.println("pasando a xml la pokedes y adestradores");

        List<Pokedex> lista3 = pokedexService.listarPokedex();
        pokedexService.toXMLPokedex("pokedexXML.xml", lista3.toArray(new Pokedex[0]));

        List<Adestrador> listaAdestrador3 = adestradorService.listarAdestradores();
        adestradorService.toXMLAdestrador("adestradorXML.xml", listaAdestrador3.toArray(new Adestrador[0]));

        System.out.println("borrando datos");

        adestradorService.eliminarTodosAdestrador();
        pokedexService.eliminarTodosPokedex();
    }
}
