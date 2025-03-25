package lk.ijse.gdse.ormcaursework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter

@Entity
@Table(name = "users")
public class User {
    @Id
    private String userName;
    private String password;
}
