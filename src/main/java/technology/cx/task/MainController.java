package technology.cx.task;

import org.springframework.web.bind.annotation.*;
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
        //TODO: выдавать ошибку, а не пустое значение, если пользователь не найден
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/create-user")
    User createUser(@RequestBody User user) {
        return repository.save(user);
    }

}
