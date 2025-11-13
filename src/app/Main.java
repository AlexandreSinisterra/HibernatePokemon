package app;

import model.Pokedex;
import services.PokedexService;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PokedexService pokedexService = new PokedexService();

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

        System.out.println("listando pokedex");

        List<Pokedex> lista = pokedexService.listarPokedex();
        for (Pokedex p: lista){
            System.out.println(p);
        }

        System.out.println("modificando 2 entradas");

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

        pokedexService.eliminarTodosPokedex();
    }
}
