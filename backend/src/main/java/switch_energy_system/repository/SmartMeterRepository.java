package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.interfacee.QueryImpl;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.SmartMeterReading;

import java.util.List;

@Repository
public class SmartMeterRepository implements QueryImpl {
    @Autowired
    MongoTemplate mongoTemplate;

    public void createSmartMeter(SmartMeter smartMeter) {
        mongoTemplate.save(smartMeter);
    }

    public void switchProviderForSingleSmartMeter(String smartMeterId, String providerName) {
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterId),
                new Update().set("providerName", providerName),
                SmartMeter.class);
    }

    public void switchProvidersForAllSmartMeters(String oldProviderName, String providerName) {
        mongoTemplate.findAndModify(getQueryForProviderName(oldProviderName),
                new Update().set("providerName", providerName),
                SmartMeter.class);
    }

    public void approveOrRejectSmartMeter(String smartMeterId, String status) {
        System.out.println(smartMeterId + " " + status);
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterId),
                new Update().set("smartMeterStatus", status),
                SmartMeter.class);
    }

    public List<SmartMeter> getAllPendingSmartMeter() {
        return mongoTemplate.find(Query.query(Criteria.where("smartMeterStatus").is("PENDING")), SmartMeter.class);
    }

    public List<SmartMeter> getAllApprovedSmartMeterByUserName(String userName) {
        return mongoTemplate.find(
                getQueryForUserName(userName).addCriteria(Criteria.where("smartMeterStatus").is("APPROVED")),
                SmartMeter.class);
    }

    public void createSmartMeterReading(SmartMeterReading smartMeterReading) {
        System.out.println("smart meter reading pojo created");
        mongoTemplate.save(smartMeterReading);
    }

    public void pushSmartMeterReadingsIntoList(SmartMeterReading smartMeterReading) {
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterReading.getSmartMeterId()),
                new Update().push("electricityReadings", smartMeterReading),
                SmartMeter.class);
    }
}
