package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
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

    public void toXMLPokedex(String rutaFichero, Pokedex... pokedexes){
        try(FileWriter writer = new FileWriter(rutaFichero)) {
            XMLStreamWriter xml = XMLOutputFactory.newDefaultFactory().createXMLStreamWriter(writer);

            xml.writeStartDocument("1.0");
            xml.writeStartElement("Pokedex");

            for (Pokedex pokedex : pokedexes){
                xml.writeStartElement("pokemon");

                xml.writeStartElement("id");
                xml.writeCharacters(String.valueOf(pokedex.getId()));
                xml.writeEndElement();

                xml.writeStartElement("nome");
                xml.writeCharacters(pokedex.getNome());
                xml.writeEndElement();

                xml.writeStartElement("peso");
                xml.writeCharacters(pokedex.getPeso().toString());
                xml.writeEndElement();

                xml.writeStartElement("Misc");
                xml.writeCharacters(pokedex.getMisc());
                xml.writeEndElement();

                xml.writeEndElement();
            }

            xml.writeEndElement();
            xml.writeEndDocument();

        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public void serializarEntradasPokedex(String fichero, Pokedex... pokedexEntries) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeInt(pokedexEntries.length);
            for (Pokedex pokedex : pokedexEntries) {
                oos.writeObject(pokedex);
            }
        } catch (IOException e) {
            System.out.println("Error serializando Pokedex: " + e.getMessage());
        }
    }

    public ArrayList<Pokedex> leerEntradasSerializadas(String fichero, int cantidadLeer) {
        ArrayList<Pokedex> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            int longitud = ois.readInt();
            for (int i = 0; i < Math.min(longitud, cantidadLeer); i++) {
                Pokedex p = (Pokedex) ois.readObject();
                list.add(p);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo serializado: " + e.getMessage());
        }
        return list;
    }

}
