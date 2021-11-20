package hsqdb;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс hsqdbTest.OrdersStoreTest - класс для тестирования OrdersStore.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class OrdersStoreTest {

    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void destroy() {
        try (var con = pool.getConnection();
             var st = con.createStatement()
        ) {
            var res = st.executeQuery("DROP TABLE orders");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByIdOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        Order res = store.findById(1);

        assertThat(res.getDescription(), is("description1"));
        assertThat(res.getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindByNameOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> res = store.findByName("name1");

        assertThat(res.get(0).getDescription(), is("description1"));
        assertThat(res.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndUpdate() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        store.update(Order.of("name2", "description2"), 1);

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description2"));
        assertThat(all.get(0).getId(), is(1));
    }

}