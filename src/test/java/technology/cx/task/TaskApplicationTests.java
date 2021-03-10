package technology.cx.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;
import technology.cx.task.database.UserRepository;
import technology.cx.task.entities.User;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TaskApplicationTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final Logger logger = LoggerFactory.getLogger(TaskApplicationTests.class);

    @Test
    void preloadDatabase() {

        ArrayList<User> usersToPreload = new ArrayList<>();

        usersToPreload.add(new User("Maksim", "Bessonov", "MyStrongPassword"));
        usersToPreload.add(new User("Maria", "Volovodova", "HerStrongPassword"));
        usersToPreload.add(new User("Sergey", "Zverev", "HisStrongPassword"));

        //Проверяем соответствие id пользователей в базе данных
        for (int i = 0; i < 3; i++) {
            Long loadedId = repository.save(usersToPreload.get(i)).getId();
            logger.info("Preloaded user: " + loadedId);
            Assert.isTrue(loadedId == i + 1);
        }

        Assert.isTrue(repository.findAll().size() == 3, "Database has been preloaded");

    }

    @Test
    void createNewUser() throws Exception {
        mockMvc.perform(post("/create-user")
                .content(objectMapper.writeValueAsString(new User("Test", "User", "123")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Maksim"))
                .andExpect(jsonPath("$.lastName").value("Bessonov"))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

}
