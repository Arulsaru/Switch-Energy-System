package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.interfacee.QueryImpl;
import switch_energy_system.pojo.SmartMeterReading;
import switch_energy_system.pojo.TotalReadings;

import java.util.List;

@Repository
public class TotalReadingsRepository implements QueryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createTotalReadingsCollection(TotalReadings totalReadings) {
        mongoTemplate.save(totalReadings);
    }

    public List<TotalReadings> getAllReadingsBySmartMeterId(String smartMeterId) {
        return mongoTemplate.find(getQueryForProviderName(smartMeterId), TotalReadings.class);
    }

    public void pushSmartMeterReadingsIntoTotalReadingList(SmartMeterReading smartMeterReading) {
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterReading.getSmartMeterId()),
                new Update().push("electricityReadings", smartMeterReading),
                TotalReadings.class);
    }

//    public void calculateReadings(SmartMeterReading smartMeterReading, TotalReadings totalReadings) {
//        smartMeterReading.setSmartMeterId("63ed1a3acdf1f32b7ba5f7ff");
//        totalReadings.setSmartMeterId("63ed1a3acdf1f32b7ba5f7ff");
//        pushSmartMeterReadingsIntoTotalReadingList(smartMeterReading, totalReadings);
//        List<TotalReadings> totalReadingsList = mongoTemplate.find(getQueryForSmartMeterId(smartMeterReading.getSmartMeterId()), TotalReadings.class);
//        System.out.println(totalReadingsList);
//    }
}
