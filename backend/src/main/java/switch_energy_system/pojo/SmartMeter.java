package switch_energy_system.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "smartMeter")
public class SmartMeter {
    private String smartMeterId;
    private String userName;
    private String providerName;
    private String readings;
    private String date;
    private long epochTime;
    private String smartMeterStatus;  // pending, approved, rejected
    private boolean isEnabled;

    public SmartMeter(String smartMeterId, String userName) {
        this.smartMeterId = smartMeterId;
        this.userName = userName;
        this.providerName = "The Blue Eco"; // default provider
        this.readings = "0";
        this.smartMeterStatus = "PENDING";
        this.epochTime = 0;
        this.isEnabled = true;
    }

}
