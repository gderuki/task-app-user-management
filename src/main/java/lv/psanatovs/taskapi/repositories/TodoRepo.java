package lv.psanatovs.taskapi.repositories;

import lv.psanatovs.taskapi.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<TodoEntity, Long> {
}
