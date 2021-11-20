package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс VacancyDb - содержит набор вакансий.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
@Entity
@Table(name = "vacancydb", schema = "job", catalog = "hql_db")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDB {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VacancyDB vacancyDB = (VacancyDB) o;
        return id == vacancyDB.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VacancyDB{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", vacancyList=" + vacancies
                + '}';
    }
}
