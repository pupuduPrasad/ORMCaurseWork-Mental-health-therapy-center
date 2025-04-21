package lk.ijse.gdse.ormcaursework.entity;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

@Entity
public class Payment {
    @Id
    private String paymentID;
    private String paymentDate;
    private int PaymentAmount;

    @ManyToOne
    @JoinColumn (name = "patientID")
    private Patient patient;

}
