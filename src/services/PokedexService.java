package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PokedexService {

    public void insertarPokedex(Pokedex p){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
    }

    public List<Pokedex> listarPokedex(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Pokedex> lista = session.createQuery("From Pokedex", Pokedex.class).list();
        session.close();
        return lista;
    }

    public Pokedex obternerPorIdPokedex(int id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Pokedex pokemon = session.get(Pokedex.class, id);
        session.close();
        return pokemon;
    }

    public void actualizarPokedex(Pokedex p){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
        session.close();
    }

    public void eliminarPokedex(Pokedex p){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(p);
        tx.commit();
        session.close();
    }

    public void eliminarTodosPokedex(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("Delete From Pokedex").executeUpdate();
        tx.commit();
        session.close();
    }
}
