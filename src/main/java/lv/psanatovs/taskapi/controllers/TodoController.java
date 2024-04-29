package lv.psanatovs.taskapi.controllers;

import lv.psanatovs.taskapi.entities.TodoEntity;
import lv.psanatovs.taskapi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoEntity todo, @RequestParam Long userId) {
        try {

            return ResponseEntity.ok(todoService.createTodo(todo, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @PutMapping
    public ResponseEntity<?> completeTodo(@RequestParam Long todoId) {
        try {
            return ResponseEntity.ok(todoService.completeTodo(todoId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }
}
