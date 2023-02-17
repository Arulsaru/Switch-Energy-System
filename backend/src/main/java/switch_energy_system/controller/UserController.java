package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.dto.JwtResponse;
import switch_energy_system.dto.LoginRequest;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.User;
import switch_energy_system.service.JwtService;
import switch_energy_system.service.SmartMeterService;
import switch_energy_system.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    SmartMeterService smartMeterService;

    @PostMapping("/signup")  // user
    public void createUser(@RequestBody User user) throws Exception {
        userService.createUser(user);
        smartMeterService.createSmartMeter(user.getUserName(), "ProviderOne"); // default provider inga kudupee
    }

    @GetMapping("/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.getUserByName(userName);
    }

    @PostMapping("/login-get-token")
    public JwtResponse authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
        System.out.println("Inside");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginRequest.getUserName());
            return new JwtResponse(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
//        return jwtService.generateToken(authRequest.getUserName()); // generate token for each and every user
    }
}
