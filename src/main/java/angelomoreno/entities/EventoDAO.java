package angelomoreno.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventoDAO {
    private final EntityManager em;
    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Evento evento) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(evento);
        transaction.commit();
        System.out.println("Nuovo evento creato correttamente");
    }

    public Evento getById(long id) {
        return em.find(Evento.class, id);
    }

    public void delete(long id) {
        Evento found = em.find(Evento.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("L'evento è stato cancellato con successo");
        } else {
            System.err.println("L'evento con id" + id + " non è stato trovato");
        }
    }

    public void refresh(long id) {
        Evento found = em.find(Evento.class, id);
        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.refresh(found);
            transaction.commit();
            System.out.println("L'evento è stato refreshato con successo");
        } else {
            System.err.println("L'evento con id" + id + " non è stato trovato");
        }
    }

}
