package store;

import model.Candidate;
import org.hibernate.Session;

import javax.persistence.Query;

/**
 * Класс HbrHqlUpdate - update with HQL.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class HbrHqlUpdate {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("---Обновление записи для кандидата---");
        Query query = session.createQuery(
                "update Candidate c set c.name = :newName, "
                        + "c.salary = :newSalary "
                        + "where c.id = :cId");
        query.setParameter("newName", "Dmitry");
        query.setParameter("newSalary", "400k/c");
        query.setParameter("cId", 1L);
        query.executeUpdate();

        System.out.println("---Выборка кандидата по id---");
        org.hibernate.Query<Candidate> query3 = session.createQuery(
                "from Candidate c where c.id = 1");
        System.out.println(query3.uniqueResult());

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();

    }
}