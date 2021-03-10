package technology.cx.task.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    /*

    Я кажется решил проблему с выводом пароля в JSON
    Просто добавил @JsonIgnore к getPassword'у

    Но мне понравилось другое решение проблемы на StackOverflow
    https://stackoverflow.com/a/49207551
    TODO: переделать скрытие пароля на понравивишийся вариант

     */

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public static class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(Long id) {
            super("Couldn't find user with id " + id);
        }

    }
}
