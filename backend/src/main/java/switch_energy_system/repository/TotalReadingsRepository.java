package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.dto.SmartMeterReadingResponse;
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

    public List<SmartMeterReadingResponse> calculateTotalReadingsOfASmartMeter() {
//        MatchOperation matchOperation = Aggregation.match(Criteria.where("isEnabled").is(true)
//                        .and(String.valueOf(Criteria.where("electricityReadings.dateAndTime").gte("2023-02-16T12:38:50.008+0000").lte("2023-02-16T12:40:20.006+0000"))));

        UnwindOperation unwindOperation = Aggregation.unwind("electricityReadings");
        MatchOperation matchOperation = Aggregation.match(Criteria.where("isEnabled").is(true));
        GroupOperation group = Aggregation.group("smartMeterId").sum("electricityReadings.readings").as("total");

        Aggregation aggregation = Aggregation.newAggregation(unwindOperation, matchOperation, group);

        AggregationResults<SmartMeterReadingResponse> result = mongoTemplate.aggregate(aggregation, TotalReadings.class, SmartMeterReadingResponse.class);
        return result.getMappedResults();
    }

    public void calculateAndStoreReading() {
        SmartMeterReading smartMeterReading = new SmartMeterReading(10);
        mongoTemplate.updateMulti(Query.query(Criteria.where("isEnabled").is(true)),
                new Update().push("electricityReadings", smartMeterReading),
                TotalReadings.class);
    }

}
