package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AdestradorService {

    public void insertarAdestrador(Adestrador a){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(a);
        tx.commit();
        session.close();
    }

    public void updateAdestrador(Adestrador a){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(a);
        tx.commit();
        session.close();
    }

    public List<Adestrador> listarAdestradores(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        List<Adestrador> adestradors = session.createQuery("From Adestrador", Adestrador.class).list();
        session.close();
        return adestradors;
    }

    public Adestrador obtenerPorIdAdestrador(int id){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Adestrador adestradorPorId = session.get(Adestrador.class,id);
        session.close();
        return adestradorPorId;
    }

    public void eliminarAdestrador(Adestrador a){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(a);
        tx.commit();
        session.close();
    }

    public void eliminarTodosAdestrador(){
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("Delete From Adestrador").executeUpdate();
        tx.commit();
        session.close();
    }

    public void toXMLAdestrador(String rutaFichero, Adestrador... adestradors){
        try(FileWriter writer = new FileWriter(rutaFichero)) {
            XMLStreamWriter xml = XMLOutputFactory.newDefaultFactory().createXMLStreamWriter(writer);

            xml.writeStartDocument("1.0");
            xml.writeStartElement("Adestradores");

            for (Adestrador adestrador : adestradors){
                xml.writeStartElement("adestrador");

                xml.writeStartElement("id");
                xml.writeCharacters(String.valueOf(adestrador.getId()));
                xml.writeEndElement();

                xml.writeStartElement("nome");
                xml.writeCharacters(adestrador.getNombre());
                xml.writeEndElement();

                xml.writeStartElement("nacemento");
                xml.writeCharacters(adestrador.getFecha().toString());
                xml.writeEndElement();

                xml.writeEndElement();
            }

            xml.writeEndElement();
            xml.writeEndDocument();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

    }
}
