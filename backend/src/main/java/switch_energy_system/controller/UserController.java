package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.User;
import switch_energy_system.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping  // user
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "User created";
    }

    @PutMapping("/enable/{providerName}")  // admin
    public String enableProvider(@PathVariable String providerName) {
        userService.enableProvider(providerName);
        return "Provider enabled";
    }

    @PutMapping("/disable/{providerName}") // admin
    public String disableProvider(@PathVariable String providerName) {
        userService.disableProvider(providerName);
        return "Provider disabled";
    }

    @GetMapping("/get_all_providers") // admin
    public List<Provider> getAllProviders() {
        return userService.getAllproviders();
    }

    @PutMapping("/{smartMeterId}/switch_providers/{providerName}") // admin
    public String switchProviderForSingleSmartMeter(@PathVariable String smartMeterId, @PathVariable String providerName) {
        userService.switchProviderForSingleSmartMeter(smartMeterId, providerName);
        return "smart meter switched";
    }

    @PutMapping("{smartMeterId}/approve/{status}")
    public String approveOrRejectSmartMeter(@PathVariable String smartMeterId, @PathVariable String status) {
        userService.approveOrRejectSmartMeter(smartMeterId, status);
        return "smart meter approved";
    }
}
