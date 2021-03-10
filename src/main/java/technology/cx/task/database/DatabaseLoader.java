package technology.cx.task.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import technology.cx.task.entities.User;

@Configuration
public class DatabaseLoader {

    //Класс отвечает за наполнение базы данных стартовыми записями

    private static  final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {

            log.info("Loading data: " + repository.save(new User("Maksim", "Bessonov", "MyStrongPassword")));
            log.info("Loading data: " + repository.save(new User("Maria", "Volovodova", "HerStrongPassword")));
            log.info("Loading data: " + repository.save(new User("Sergey", "Zverev", "HisStrongPassword")));

        };
    }

}
