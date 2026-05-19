package lk.ijse.serenitymentalhealthsystem.tm;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TherapyProgramTM {
    private String programId;

    private String programName;

    private String therapyType;

    private int duration;

    private String frequency;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private String status;

}
