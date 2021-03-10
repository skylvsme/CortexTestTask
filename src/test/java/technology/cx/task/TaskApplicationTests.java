package technology.cx.task;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import technology.cx.task.database.UserRepository;
import technology.cx.task.entities.User;

import java.util.ArrayList;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    private UserRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(TaskApplicationTests.class);

    @Test
    void preloadDatabase() {

        ArrayList<User> usersToPreload = new ArrayList<>();

        usersToPreload.add(new User("Maksim", "Bessonov", "MyStrongPassword"));
        usersToPreload.add(new User("Maria", "Volovodova", "HerStrongPassword"));
        usersToPreload.add(new User("Sergey", "Zverev", "HisStrongPassword"));

        //Проверяем соответствие id пользователей в базе данных
        for (int i = 0; i < 3; i++) {
            Long loadedId =  repository.save(usersToPreload.get(i)).getId();
            logger.info("Preloaded user: " + loadedId);
            Assert.isTrue(loadedId == i + 1);
        }

        Assert.isTrue(repository.findAll().size() == 3, "Database has been preloaded");

    }

}
