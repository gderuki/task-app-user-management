package lv.psanatovs.taskapi.models;

import lv.psanatovs.taskapi.entities.TodoEntity;

public class TodoModel {
    private Long id;
    private String title;
    private Boolean completed;

    public static TodoModel fromEntity(TodoEntity entity) {
        TodoModel model = new TodoModel();

        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setCompleted(entity.getCompleted());

        return model;
    }

    public TodoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
