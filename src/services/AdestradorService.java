package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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

    public ArrayList<Adestrador> leerXMLAdestradores(String fichero) {
        ArrayList<Adestrador> adestradors = new ArrayList<>();
        String elementoActual = null;
        try (FileReader fileReader = new FileReader(fichero)) {
            XMLStreamReader xmlReader = XMLInputFactory.newDefaultFactory().createXMLStreamReader(fileReader);
            Adestrador adestrador = null;
            while (xmlReader.hasNext()) {
                int evento = xmlReader.next();
                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        elementoActual = xmlReader.getLocalName();
                        if (elementoActual.equals("adestrador")) {
                            adestrador = new Adestrador();
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (adestrador == null) continue;
                        if (xmlReader.isWhiteSpace()) continue;   // <-- MUY IMPORTANTE
                        String valor = xmlReader.getText().trim();
                        switch (elementoActual) {
                            case "id" -> adestrador.setId(Integer.parseInt(valor));
                            case "nome" -> adestrador.setNombre(valor);
                            case "nacemento" -> {
                                String soloFecha = valor.substring(0, 10);
                                adestrador.setFecha(Date.valueOf(soloFecha));
                            }
                        }
                    }
                    case XMLStreamConstants.END_ELEMENT -> {
                        String nombre = xmlReader.getLocalName();
                        if (nombre.equals("adestrador")) {
                            adestradors.add(adestrador);
                            adestrador = null;
                        }
                        elementoActual = null;
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            System.out.println("Error leyendo XML adestradores: " + e.getMessage());
        }
        return adestradors;
    }


}
