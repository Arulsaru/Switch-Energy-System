package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.User;
import switch_energy_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping  // user
    public void createUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
    }
}
