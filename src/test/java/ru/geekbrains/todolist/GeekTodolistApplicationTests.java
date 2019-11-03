package ru.geekbrains.todolist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ru.geekbrains.todolist.persist.entity.User;
import ru.geekbrains.todolist.persist.repo.ToDoRepository;
import ru.geekbrains.todolist.persist.repo.UserRepository;
import ru.geekbrains.todolist.repr.ToDoRepr;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql(scripts = "classpath:test_data.sql")
public class GeekTodolistApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(GeekTodolistApplicationTests.class);

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        logger.info("Query by username");
        List<ToDoRepr> todos = toDoRepository.findToDoByUser_Username("user1");
    }

	@Test
	public void testFindByUser1() {
    	User user = userRepository.getUserByUsername("user1").
				orElseThrow(AssertionError::new);

		logger.info("Query by User");
		List<ToDoRepr> toDoByUser = toDoRepository.findToDoByUser(user);
	}

	@Test
	public void testFindByUser2() {
    	User user = new User();
		user.setId(1L);

		logger.info("Query by User");
		List<ToDoRepr> toDoByUser = toDoRepository.findToDoByUser(user);
	}

}
