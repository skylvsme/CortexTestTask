package technology.cx.task.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class User {

    //Скрытие пароля при запросе сделано костылем (наверное)
    //Я просто убрал метод getPassword

    //Погуглив я так и не нашёл, как выводить конкретные поля в JSON,
    //оставляя нужные мне методы в сущности

    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
