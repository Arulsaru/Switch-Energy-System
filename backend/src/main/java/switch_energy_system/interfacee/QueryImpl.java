package switch_energy_system.interfacee;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public interface QueryImpl {
    public default Query getQueryForProviderName(String providerName) {
        return Query.query(Criteria.where("providerName").is(providerName));
    }
}
