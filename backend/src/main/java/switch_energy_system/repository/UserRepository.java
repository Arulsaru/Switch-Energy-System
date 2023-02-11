package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.interfacee.QueryImpl;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.User;

import java.util.List;

@Repository
public class UserRepository implements QueryImpl {
    @Autowired
    MongoTemplate mongoTemplate;

    public void createUser(User user) {
        mongoTemplate.save(user);
    }

    public void enableOrDisableProvider(String providerName, boolean toEnable) {
        mongoTemplate.findAndModify(getQueryForProviderName(providerName), new Update().set("isEnabled", toEnable), Provider.class);
    }

    public boolean isProviderEnabled(String providerName) {
        return mongoTemplate.findOne(getQueryForProviderName(providerName), Provider.class).isEnabled();
    }

    public List<Provider> getAllProviders() {
        return mongoTemplate.findAll(Provider.class);
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
        mongoTemplate.findAndModify(getQueryForSmartMeterId(smartMeterId),
                new Update().set("smartMeterStatus", status),
                SmartMeter.class);
    }
}
