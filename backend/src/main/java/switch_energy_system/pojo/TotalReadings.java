package switch_energy_system.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "totalReadings")
public class TotalReadings {
    private String smartMeterId;
    private List<SmartMeterReading> electricityReadings;
    public TotalReadings(String smartMeterId) {
        this.smartMeterId = smartMeterId;
    }
}
