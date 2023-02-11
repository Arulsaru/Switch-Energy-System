package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.Provider;
import switch_energy_system.service.ProviderService;

import java.util.List;

@RestController
@RequestMapping("provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;
    @PostMapping
    public String createProvider(Provider provider) { // only admin could create provider
        providerService.createProvider(provider);
        return "Provider created";
    }
    @PutMapping
    public String enableProvider(String providerName) {
        providerService.enableProvider(providerName);
        return "Provider enabled";
    }
    @PutMapping
    public String disableProvider(String providerName) {
        providerService.disableProvider(providerName);
        return "Provider enabled";
    }
}
