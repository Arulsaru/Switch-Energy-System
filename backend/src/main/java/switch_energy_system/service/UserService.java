package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.User;
import switch_energy_system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ValidatorService userValidator;

    public void createUser(User user) throws Exception {
        userValidator.preValidate(user);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password encode pannanu
        userRepository.createUser(user);
    }

    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }
}
