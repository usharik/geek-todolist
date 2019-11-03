package ru.geekbrains.todolist.persist.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.todolist.persist.entity.ToDo;
import ru.geekbrains.todolist.persist.entity.User;
import ru.geekbrains.todolist.repr.ToDoRepr;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    List<ToDoRepr> findToDoByUser_Username(String username);

    List<ToDoRepr> findToDoByUser(User user);
}
