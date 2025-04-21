package lk.ijse.gdse.ormcaursework.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "TherapyPrograms")
public class TherapyProgram {
    @Id
    @Column(nullable = false, unique = true)
    private String programID;
    private String programName;
    private String programDuration;
    private String programFee;

//    @OneToMany (mappedBy = "therapyProgram" , cascade = CascadeType.ALL)
//    private List<ProgramDetails> programDetails = new ArrayList<>();
}
