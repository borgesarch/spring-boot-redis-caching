package core.caching.presentation.controller.todos;

import core.caching.application.todos.ITodoService;
import core.caching.domain.todos.Todo;
import core.caching.infrastructure.repositories.todos.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @Autowired
    private ITodoRepository todoRepository;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(
                    todoService.findAll());
        }catch (Exception ex){
            return new ResponseEntity<>(
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(String id){
        try{
            return ResponseEntity.ok(
                    todoRepository.findById(id));
        }catch (Exception ex){
            return new ResponseEntity<>(
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(String id){
        try{
            todoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }catch (Exception ex){
            return new ResponseEntity<>(
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Todo todo){
        try{
            return ResponseEntity.ok(
                    todoService.save(todo));
        }catch (Exception ex){
            return new ResponseEntity<>(
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Todo todo){
        try{
            return ResponseEntity.ok(
                    todoService.update(todo));
        }catch (Exception ex){
            return new ResponseEntity<>(
                    ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
