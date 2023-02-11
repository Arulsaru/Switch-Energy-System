package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import switch_energy_system.interfacee.QueryImpl;
import switch_energy_system.pojo.Provider;

import java.util.List;

@Repository
public class ProviderRepository implements QueryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createProvider(Provider provider) {
        mongoTemplate.save(provider);
    }

    public Provider getProviderByName(String providerName) {
        return mongoTemplate.findOne(getQueryForProviderName(providerName), Provider.class);
    }
}
