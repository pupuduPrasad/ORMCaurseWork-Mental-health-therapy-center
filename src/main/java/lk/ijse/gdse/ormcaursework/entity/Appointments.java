package lk.ijse.gdse.ormcaursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "appointments")

public class Appointments implements SuperEntity{
    @Id
    private String sessionId;
    private String time;
    private String notes;
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_Id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Therapist therapist;

    @OneToOne
    @JoinColumn(name = "payment_ID")
    private Payment payment;

    private String status;
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "NO";
        }
    }

}



