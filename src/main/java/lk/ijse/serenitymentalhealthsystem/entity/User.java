package lk.ijse.serenitymentalhealthsystem.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}
