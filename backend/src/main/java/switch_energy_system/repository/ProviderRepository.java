package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.interfacee.QueryImpl;
import switch_energy_system.pojo.Provider;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProviderRepository implements QueryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createProvider(Provider provider) {
        mongoTemplate.save(provider);
    }

    public Provider getProviderByProviderName(String providerName) {
        return mongoTemplate.findOne(getQueryForProviderName(providerName), Provider.class);
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

    public List<Provider> getAllProviders(long pageNumber, long pageSize) {
        return mongoTemplate.find(new Query().skip(pageNumber * pageSize).limit((int) pageSize), Provider.class);
    }

    public void addSmartMeterToTheProviderList(String smartMeterId, String providerName) {
        mongoTemplate.findAndModify(getQueryForProviderName(providerName),
                new Update().push("smartMetersList", smartMeterId),
                Provider.class);
    }

    public List<Provider> getAllSmartMetersFromProviderList(String providerName) {
        return mongoTemplate.find(getQueryForProviderName(providerName).addCriteria(Criteria.where("providerList")), Provider.class);
    }

    public List<Provider> getAllEnabledProviders() {
        return mongoTemplate.findAll(Provider.class).stream().filter(Provider::isEnabled).collect(Collectors.toList());
    }

    public void removeSmartMeterFromProviderList(String smartMeterId, String providerName) {
        mongoTemplate.findAndModify(getQueryForProviderName(providerName),
                new Update().pull("smartMeterList", null),
                Provider.class);
    }

    public void switchSmartMeterListBetweenProviders(String oldProviderName, String newProviderName) {
        System.out.println(getAllSmartMetersFromProviderList(oldProviderName));
    }
}
