package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.User;
import switch_energy_system.service.SmartMeterService;
import switch_energy_system.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SmartMeterService smartMeterService;

    @PostMapping  // user
    public void createUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
        smartMeterService.createSmartMeter(user.getUserName(), "ProviderOne"); // default provider inga kudupee
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByName(userName);
    }
}
