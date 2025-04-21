package lk.ijse.gdse.ormcaursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "therapy_programs")
public class TPrograms implements SuperEntity {
    @Id
    private String programID;
    private String programName;
    @Column(length = 100)
    private String programDescription;
    private Double programFee;

    @OneToMany(mappedBy = "tPrograms",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ProgramDetails> programDetails;
}
