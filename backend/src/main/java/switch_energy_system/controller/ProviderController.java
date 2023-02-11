package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.Provider;
import switch_energy_system.service.ProviderService;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @PostMapping
    public String createProvider(@RequestBody Provider provider) { // only admin could create provider
        providerService.createProvider(provider);
        return "Provider created";
    }
}
