package ru.karachurin.restaurants.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.karachurin.restaurants.model.Role;
import ru.karachurin.restaurants.model.User;
import ru.karachurin.restaurants.util.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.karachurin.restaurants.testData.UserTestData.*;

/**
 * Created by Денис on 24.11.2016.
 */
public class UserServiceTest extends AbstractServiceTest{
    @Autowired
    UserService service;

    @Test
    public void save() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.ROLE_USER));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        assertThat(service.getAll(), is(Arrays.asList(USER, ADMIN, created)));
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertEquals(Arrays.asList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertEquals(user, ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        assertEquals(user, ADMIN);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        service.update(updated);
        assertEquals(updated, service.get(USER_ID));
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertThat(Arrays.asList(USER, ADMIN), is(all));
    }

    @Test
    public void enable() throws Exception {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }
}