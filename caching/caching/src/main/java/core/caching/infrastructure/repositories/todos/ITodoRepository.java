package core.caching.infrastructure.repositories.todos;

import core.caching.domain.todos.Todo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITodoRepository extends JpaRepository<Todo, String> {
    @Query(
            value = "select * from todos c where (cast(?1 as text) IS NULL OR c.id LIKE cast(?1 as text))",
            nativeQuery = true
    )
    @Cacheable(value = "todo", key = "#id", condition = "#id != null")
    Optional<Todo> findById(String id);
}
