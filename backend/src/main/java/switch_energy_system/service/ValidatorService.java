package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.User;
import switch_energy_system.repository.UserRepository;

@Service
public class ValidatorService {

    @Autowired
    UserRepository userRepository;

    public void preValidate(User user) throws Exception {
        if(userRepository.getUserByName(user.getUserName()) != null) {
            throw new Exception("UserName is already used.. try entering another username");
        }
    }
}
