package lv.psanatovs.taskapi.models;

import lv.psanatovs.taskapi.entities.UserEntity;

import java.util.List;

public class UserModel {
    private Long id;
    private String username;
    private List<TodoModel> todos;

    public static UserModel fromEntity(UserEntity entity) {
        UserModel model = new UserModel();

        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setTodos(entity.getTodos().stream().map(TodoModel::fromEntity).toList());

        return model;
    }

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();

        entity.setId(this.getId());
        entity.setUsername(this.getUsername());

        return entity;
    }

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TodoModel> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoModel> todos) {
        this.todos = todos;
    }
}
