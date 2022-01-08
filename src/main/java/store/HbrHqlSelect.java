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
        Candidate two = new Candidate(2L, "Petr", "Lead", "400k/c", null);
        Candidate three = new Candidate(3L, "Vsevolod", "DBDeveloper", "500k/c", null);
        Candidate four = new Candidate(3L, "Petr", "JavaDeveloper", "600k/c", null);

        System.out.println("---Сохраняем кандидатов в БД---");
        session.save(one);
        session.save(two);
        session.save(three);
        session.save(four);

        System.out.println("---Выборка всех кандидатов---");
        Query allCandidates = session.createQuery("from Candidate");
        allCandidates.setFirstResult(0);
        allCandidates.setMaxResults(8);
        List<Candidate> candidates1 = allCandidates.getResultList();

        for (Candidate c : candidates1) {
            System.out.println(c);
        }

        System.out.println("---Выборка и вывод с 2 по 4 кандидатов из набора---");
        Query candidatesByRange = session.createQuery("from Candidate");
        candidatesByRange.setFirstResult(1);
        candidatesByRange.setMaxResults(3);
        List<Candidate> candidates2 = candidatesByRange.getResultList();

        for (Candidate c : candidates2) {
            System.out.println(c);
        }

        System.out.println("---Выборка кандидата по id---");
        org.hibernate.Query<Candidate> candidateById = session.createQuery(
                "from Candidate c where c.id = 1");
        System.out.println(candidateById.uniqueResult());

        System.out.println("---Выборка кандидата по имени---");
        org.hibernate.Query<Candidate> candidateByName =
                session.createQuery("from Candidate c where c.name = :name");
        candidateByName.setParameter("name", "Parfiry");
        List<Candidate> candidates4 = candidateByName.getResultList();

        for (Candidate c : candidates4) {
            System.out.println(c);
        }

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
