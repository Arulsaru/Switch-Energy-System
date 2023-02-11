package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.pojo.Provider;

@Repository
public class ProviderRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public void createProvider(Provider provider) {
        mongoTemplate.save(provider);
    }
    public Provider getProviderByName(String providerName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("providerName").is(providerName)), Provider.class);
    }
    public void enableOrDisableProvider(String providerName, boolean toEnable) {
        mongoTemplate.findAndModify(Query.query(Criteria.where("providerName").is(providerName)), new Update().set("isEnabled", toEnable), Provider.class);
    }
    public boolean isProviderEnabled(String providerName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("providerName").is(providerName)), Provider.class).isEnabled();
    }
}
