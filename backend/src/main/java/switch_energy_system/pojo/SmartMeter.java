package switch_energy_system.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Data
@Document(collection = "smartMeter")
public class SmartMeter {
    @Id
    private String smartMeterId;
    private String userName;
    private String providerName;
    private String readings;
    private long amount;
    private String date;
    private long epochTime;
    private String smartMeterStatus;  // pending, approved, rejected
    private boolean isEnabled;

    public SmartMeter(String userName) {
        this.userName = userName;
        this.readings = "0";
        this.date = getCurrentDateAndTimeInGMT();
        this.smartMeterStatus = "PENDING";
        this.epochTime = Instant.now().toEpochMilli();
        this.isEnabled = true;
    }

    public String getCurrentDateAndTimeInGMT() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("ISO"));
        return dateFormat.format(new Date()); // 2023-02-15 06:48
    }
}
