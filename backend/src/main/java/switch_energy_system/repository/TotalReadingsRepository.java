package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public TotalReadings getAllReadingsBySmartMeterId(String smartMeterId) {
        return mongoTemplate.findOne(getQueryForSmartMeterId(smartMeterId), TotalReadings.class);
    }

    public void pushSmartMeterReadingsIntoTotalReadingList(SmartMeterReading smartMeterReading) {
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterReading.getSmartMeterId()),
                new Update().push("electricityReadings", smartMeterReading),
                TotalReadings.class);
    }

//    public List<TotalReadings> calculateTotalReadingsOfASmartMeter(String smartMeterId) {
//        return mongoTemplate.find(
//                Query.query(Criteria.where("electricityReadings.readings").exists(true)
//
//
//                ),
//                TotalReadings.class);
//    }

    public void calculateAndStoreReading() {
        SmartMeterReading smartMeterReading = new SmartMeterReading(10);
        mongoTemplate.updateMulti(Query.query(Criteria.where("isEnabled").is(true)),
                new Update().push("electricityReadings", smartMeterReading),
                TotalReadings.class);
    }
}
