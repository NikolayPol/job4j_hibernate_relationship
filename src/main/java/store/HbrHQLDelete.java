package store;

import model.Candidate;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * Класс HbrHQLDelete - delete with HQL
 * Класс с тестами
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class HbrHQLDelete {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("---Удаление записи для кандидата---");
        Query query = session.createQuery(
                "delete from Candidate c where c.id = :cId");
        query.setParameter("cId", 3L);
        query.executeUpdate();

        System.out.println("---Выборка всех кандидатов---");
        Query query1 = session.createQuery("from Candidate");
        List<Candidate> candidates1 = query1.getResultList();

        for (Candidate c : candidates1) {
            System.out.println(c);
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

    }
}
