package switch_energy_system.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Data
@Getter
@Setter
public class SmartMeterReading {

    private String smartMeterId;
    private String dateAndTime;
    private long epochTimeStamp;
    private long readings;

    public SmartMeterReading(long readings) {
        this.dateAndTime = getCurrentDateAndTimeInGMT();
        this.epochTimeStamp = Instant.now().toEpochMilli();
        this.readings = readings;
    }

    public String getCurrentDateAndTimeInGMT() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date()); // 2023-02-15 06:48
    }
}
