package core.caching.application.todos;

import core.caching.domain.todos.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> findAll();
    Todo findById(Long id);
    Todo save(Todo todo);
    Todo update(Todo todo);
}
