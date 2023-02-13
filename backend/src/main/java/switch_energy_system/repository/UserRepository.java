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

    public User getUserByName(String userName) {
        return mongoTemplate.findOne(getQueryForUserName(userName), User.class);
    }
}
