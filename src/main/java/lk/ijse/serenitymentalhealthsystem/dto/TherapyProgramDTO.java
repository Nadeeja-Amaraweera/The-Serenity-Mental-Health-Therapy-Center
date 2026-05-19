package lk.ijse.serenitymentalhealthsystem.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class TherapyProgramDTO {
    private String programId;

    private String programName;

    private String therapyType;

    private int duration;

    private String frequency;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String description;
}
