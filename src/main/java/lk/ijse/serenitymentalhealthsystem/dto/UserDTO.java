package lk.ijse.serenitymentalhealthsystem.dto;

import lk.ijse.serenitymentalhealthsystem.entity.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private Role role;
}
