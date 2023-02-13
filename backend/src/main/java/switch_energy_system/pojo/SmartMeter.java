package switch_energy_system.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Locale;

@Data
@Document(collection = "smartMeter")
public class SmartMeter {
    @Id
    private String smartMeterId;
    private String userName;
    private String providerName;
    private String readings;
    private String date;
    private long epochTime;
    private String smartMeterStatus;  // pending, approved, rejected
    private boolean isEnabled;
    private static int index = 1;

    public SmartMeter(String smartMeterId, String userName) {
        this.userName = userName;
        this.providerName = "The Blue Eco"; // default provider
        this.readings = "0";
        this.date = "0";
        this.smartMeterStatus = "PENDING";
        this.epochTime = 0;
        this.isEnabled = true;
    }
}
