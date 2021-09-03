package core.caching.domain.todos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column
    private String name;

}
