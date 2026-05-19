package lk.ijse.serenitymentalhealthsystem.tm;

import javafx.scene.layout.HBox;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PatientTM {
    private Long patientId;
    private String fullName;
    private Integer age;
    private String gender;
    private String phone;
    private String email;
//    private Integer programCount;
//    private String registrationDate;
    private String status;
    private HBox actions;
}
