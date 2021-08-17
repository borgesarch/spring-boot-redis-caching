package core.caching.infrastructure.repositories.todos;

import core.caching.domain.todos.Todo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends JpaRepository<Todo, Long> {
    @Cacheable(value = "todos", key = "#id")
    Todo findById(Integer id);
}
