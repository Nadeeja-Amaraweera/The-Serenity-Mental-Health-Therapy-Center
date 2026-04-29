package lk.ijse.serenitymentalhealthsystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
