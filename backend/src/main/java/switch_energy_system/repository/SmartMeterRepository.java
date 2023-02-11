package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import switch_energy_system.pojo.SmartMeter;

public class SmartMeterRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public void createSmartMeter(SmartMeter smartMeter) {
        mongoTemplate.save(smartMeter);
    }
}
