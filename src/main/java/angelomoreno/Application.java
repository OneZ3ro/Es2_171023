package angelomoreno;

import angelomoreno.entities.Evento;
import angelomoreno.entities.EventoDAO;
import angelomoreno.entities.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneventi");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        try {
            EventoDAO ed = new EventoDAO(em);
            LocalDate oggi = LocalDate.now();

//            Evento evento2 = new Evento("Festa del lavoro", oggi, "Penso sarà noioso", TipoEvento.PRIVATO, 30);
//            ed.save(evento2);

            Evento eventoFromDB = ed.getById(1);
            if (eventoFromDB != null) {
                System.out.println(eventoFromDB);
            }

//            ed.delete(2);

            Evento refreshEventoFromDB = ed.getById(2);
            if (refreshEventoFromDB != null){
                refreshEventoFromDB.setTitolo("Party lavoro");
                System.out.println("PRE-REFRESH");
                System.out.println(refreshEventoFromDB);

                ed.refresh(2);
                System.out.println("POST REFRESH");
                System.out.println(refreshEventoFromDB);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
