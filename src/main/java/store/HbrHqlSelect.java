package store;

import model.Candidate;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.List;

/**
 * Класс HbrHqlSelect - create and select with HQL.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class HbrHqlSelect {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        System.out.println("---Создаем кандидатов---");
        Candidate one = new Candidate("Parfiry", "JavaDeveloper", "300k/c");
        Candidate two = new Candidate(2L, "Petr", "Lead", "400k/c");
        Candidate three = new Candidate(3L, "Vsevolod", "DBDeveloper", "500k/c");
        Candidate four = new Candidate(3L, "Petr", "JavaDeveloper", "600k/c");

        System.out.println("---Сохраняем кандидатов в БД---");
        session.save(one);
        session.save(two);
        session.save(three);
        session.save(four);

        System.out.println("---Выборка всех кандидатов---");
        Query query1 = session.createQuery("from Candidate");
        query1.setFirstResult(0);
        query1.setMaxResults(8);
        List<Candidate> candidates1 = query1.getResultList();

        for (Candidate c : candidates1) {
            System.out.println(c);
        }

        System.out.println("---Выборка и вывод с 2 по 4 кандидатов из набора---");
        Query query2 = session.createQuery("from Candidate");
        query2.setFirstResult(1);
        query2.setMaxResults(3);
        List<Candidate> candidates2 = query2.getResultList();

        for (Candidate c : candidates2) {
            System.out.println(c);
        }

        System.out.println("---Выборка кандидата по id---");
        org.hibernate.Query<Candidate> query3 = session.createQuery(
                "from Candidate c where c.id = 1");
        System.out.println(query3.uniqueResult());

        System.out.println("---Выборка кандидата по имени---");
        org.hibernate.Query<Candidate> query4 =
                session.createQuery("from Candidate c where c.name = :name");
        query4.setParameter("name", "Parfiry");
        List<Candidate> candidates4 = query4.getResultList();

        for (Candidate c : candidates4) {
            System.out.println(c);
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
