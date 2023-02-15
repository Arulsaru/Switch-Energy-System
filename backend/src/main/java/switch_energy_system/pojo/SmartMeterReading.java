package switch_energy_system.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Data
@Document(collection = "smartMeterReadings")
public class SmartMeterReading {

    private String smartMeterId;
    private String dateAndTime;
    private long epochTimeStamp;

    public SmartMeterReading(String smartMeterId) {
        this.smartMeterId = smartMeterId;
        this.dateAndTime = getCurrentDateAndTimeInGMT();
        this.epochTimeStamp = Instant.now().toEpochMilli();
    }

    public String getCurrentDateAndTimeInGMT() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date()); // 2023-02-15 06:48
    }
}
