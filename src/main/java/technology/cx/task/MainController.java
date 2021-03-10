package technology.cx.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import technology.cx.task.database.UserRepository;
import technology.cx.task.entities.User;

import java.util.List;

@RestController
public class MainController {

    private final UserRepository repository;

    MainController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/get-all-users")
    List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User userById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

}
