package switch_energy_system.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    private String userName;
    private String password;
    private long phoneNumber;
    private String email;
    private String role;
    public User(String userName, String password, long phoneNumber, String email) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = "ADMIN";
    }
}