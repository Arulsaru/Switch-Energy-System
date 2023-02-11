package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.User;
import switch_energy_system.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void createUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.createUser(user);
    }
}
