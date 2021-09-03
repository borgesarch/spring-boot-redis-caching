package core.caching.application.todos;

import core.caching.domain.todos.Todo;
import core.caching.infrastructure.repositories.todos.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TodoService implements ITodoService{

    @Autowired
    private ITodoRepository todoRepository;

    @Cacheable(cacheNames = "todo", key="#root.method.name")
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    @CacheEvict(cacheNames = "todo", allEntries = true)
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    @CachePut(cacheNames = "todo", key="#todo.getId()")
    public Todo update(final Todo todo) {
        return todoRepository.save(todo);
    }
}
