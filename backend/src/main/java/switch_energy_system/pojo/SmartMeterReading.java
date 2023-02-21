package switch_energy_system.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Data
@Getter
@Setter
public class SmartMeterReading {
    @Id
    private String smartMeterId;
    private LocalDateTime dateAndTime;
    private long epochTimeStamp;
    private long readings;

    public SmartMeterReading(long readings) {
        this.dateAndTime = LocalDateTime.now();
        this.epochTimeStamp = Instant.now().toEpochMilli();
        this.readings = readings;
    }
}
