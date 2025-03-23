package lk.ijse.gdse.ormcaursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

@Entity
public class User {
    @Id
    private String userName;
    private String password;
    private String userRole;
}
