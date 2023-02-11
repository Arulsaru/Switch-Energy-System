package switch_energy_system.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document(collection = "provider")
public class Provider {
    private String providerName;
    private double ratePerWatt;
    private List<String> usersList; // list of users using that provider
    private boolean isEnabled;
    public Provider(String providerName, double ratePerWatt) {
        this.providerName = providerName;
        this.ratePerWatt = ratePerWatt;
        this.usersList = new ArrayList<>();
        this.isEnabled = true;
    }
}