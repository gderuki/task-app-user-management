package lv.psanatovs.taskapi.services;

import lv.psanatovs.taskapi.entities.TodoEntity;
import lv.psanatovs.taskapi.exceptions.TodoNotFoundException;
import lv.psanatovs.taskapi.models.TodoModel;
import lv.psanatovs.taskapi.repositories.TodoRepo;
import lv.psanatovs.taskapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;

    @Autowired
    private UserRepo userRepo;

    public TodoModel createTodo(TodoEntity todo, Long userId) {
        userRepo.findById(userId).ifPresent(todo::setUser);

        return TodoModel.fromEntity(todoRepo.save(todo));
    }

    public TodoModel completeTodo(Long todoId) {
        TodoEntity todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found!"));

        todo.setCompleted(true);

        return TodoModel.fromEntity(todoRepo.save(todo));
    }
}
