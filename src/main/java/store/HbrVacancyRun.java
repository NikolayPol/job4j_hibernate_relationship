package store;

import model.Candidate;
import model.Vacancy;
import org.hibernate.Session;

import java.util.List;

/**
 * Класс VacancyDb - база данных с вакансиями.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
class HbrVacancyRun {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Candidate> result = session.createQuery(
                        "select distinct can from Candidate can "
                                + "join fetch can.vacancyDB vdb "
                                + "join fetch vdb.vacancies "
                                + "where can.id = :sId", Candidate.class
                ).setParameter("sId", 14L).getResultList();
        System.out.println(result.get(0).getVacancyDB().getVacancies());

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
