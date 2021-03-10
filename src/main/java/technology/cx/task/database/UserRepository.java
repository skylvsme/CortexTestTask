package technology.cx.task.database;

import org.springframework.data.jpa.repository.JpaRepository;
import technology.cx.task.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
