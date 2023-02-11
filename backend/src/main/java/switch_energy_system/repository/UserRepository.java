package switch_energy_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import switch_energy_system.pojo.User;
@Repository
public class UserRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public void createUser(User user) {
        mongoTemplate.save(user);
    }
}
