package services;

import config.HibernateConfig;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PokemonService {

    public void crearPokemon(Pokemon p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
    }

    public Pokemon obtenerPorId(int id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Pokemon p = session.get(Pokemon.class, id);
        session.close();
        return p;
    }

    public List<Pokemon> obtenerTodosPokemon() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Pokemon> pokemons = session.createQuery("FROM Pokemon", Pokemon.class).list();
        session.close();
        return pokemons;
    }

    public void actualizarPokemon(Pokemon p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
        session.close();

    }

    public void borrarPokemon(Pokemon p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(p);
        tx.commit();
        session.close();
    }

    public void borrarTodosPokemon() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("Delete From Pokemon").executeUpdate();
        tx.commit();
        session.close();
    }

}
