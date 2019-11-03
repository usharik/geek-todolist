package ru.geekbrains.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.todolist.persist.entity.ToDo;
import ru.geekbrains.todolist.persist.repo.ToDoRepository;
import ru.geekbrains.todolist.persist.repo.UserRepository;
import ru.geekbrains.todolist.repr.ToDoRepr;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static ru.geekbrains.todolist.security.Utils.getCurrentUser;

@Service
@Transactional
public class ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public Optional<ToDoRepr> findById(Long id) {
        return toDoRepository.findById(id)
                .map(ToDoRepr::new);
    }

    public List<ToDoRepr> findToDoByUser_Username(String username) {
        return toDoRepository.findToDoByUser_Username(username);
    }

    public void save(ToDoRepr toDoRepr) {
        getCurrentUser()
                .flatMap(userRepository::getUserByUsername)
                .ifPresent(user -> {
                    ToDo toDo = new ToDo();
                    toDo.setId(toDoRepr.getId());
                    toDo.setDescription(toDoRepr.getDescription());
                    toDo.setTargetDate(toDoRepr.getTargetDate());
                    toDo.setUser(user);
                    toDoRepository.save(toDo);
                });
    }

    public void delete(Long id) {
        toDoRepository.findById(id)
                .ifPresent(toDo -> toDoRepository.delete(toDo));
    }
}
