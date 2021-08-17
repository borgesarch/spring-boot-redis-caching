package core.caching.application.todos;

import core.caching.domain.todos.Todo;
import core.caching.infrastructure.repositories.todos.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService{

    @Autowired
    private ITodoRepository todoRepository;

    @Autowired
    private RedisTemplate<Long, Todo> redisTemplate;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    @Cacheable(value = "todos", key = "#id")
    public Todo findById(Long id) {
        return redisTemplate.opsForValue()
                .get(id);
    }

    @Override
    public Todo save(Todo todo) {
        Todo todoSaved = todoRepository.save(todo);
        redisTemplate.opsForValue().set(todoSaved.getId(), todoSaved);
        return redisTemplate.opsForValue()
                .get(todoSaved.getId());
    }

    @Override
    public Todo update(Todo todo) {
        Todo todoSaved = todoRepository.save(todo);
        redisTemplate.opsForValue().set(todoSaved.getId(), todo);
        return redisTemplate.opsForValue()
                .get(todoSaved.getId());
    }
}
