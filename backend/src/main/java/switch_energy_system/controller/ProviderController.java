package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.Provider;
import switch_energy_system.service.ProviderService;

import java.util.List;

@RestController
@RequestMapping("provider")
@CrossOrigin("*")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @PostMapping
    public void createProvider(@RequestBody Provider provider) { // only admin could create provider
        providerService.createProvider(provider);
    }

    @PutMapping("/enable/{providerName}")  // admin
    public void enableProvider(@PathVariable String providerName) {
        providerService.enableProvider(providerName);
    }

    @PutMapping("/disable/{providerName}") // admin
    public void disableProvider(@PathVariable String providerName) throws Exception {
        providerService.disableProvider(providerName);
    }

    @GetMapping("/get-all-providers") // admin
    public List<Provider> getAllProviders(@RequestParam long pageNumber, @RequestParam long pageSize) {
        return providerService.getAllProviders(pageNumber, pageSize);
    }

    @GetMapping("/get-all-enabled-providers")
    public List<Provider> getAllEnabledProviders() {
        return providerService.getAllEnabledProviders();
    }
}
