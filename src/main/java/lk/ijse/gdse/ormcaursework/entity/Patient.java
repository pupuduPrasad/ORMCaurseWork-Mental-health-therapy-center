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
@Table(name = "patient")
public class Patient implements SuperEntity {
    @Id
    private String patientID;
    private String patientName;
    private String patientBirthDate;
    private String patientNIC;
    private String patientGender;
    @Column(length = 100)
    private String patientAddress;
    private String patientPhone;
    private String patientEmail;

    @OneToMany(mappedBy = "patient" ,cascade = CascadeType.ALL)
    private List<Appointments> appointments;


    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<ProgramDetails> programDetails;

}
