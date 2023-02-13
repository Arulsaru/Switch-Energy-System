package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.service.ProviderService;
import switch_energy_system.service.SmartMeterService;

import java.util.List;

@RestController
@RequestMapping("smartmeter")
@CrossOrigin("*")
public class SmartMeterController {

    @Autowired
    SmartMeterService smartMeterService;

    @Autowired
    ProviderService providerService;

    @PostMapping
    public void createSmartMeter(@RequestBody SmartMeter smartMeter) {
        smartMeterService.createSmartMeter(smartMeter);
    }

    @GetMapping
    public List<SmartMeter> getAllPendingSmartMeter() {
        return smartMeterService.getAllPendingSmartMeter();
    }

    @PutMapping("/{smartMeterId}/switch-providers/{providerName}") // admin
    public void switchProviderForSingleSmartMeter(@PathVariable String smartMeterId, @PathVariable String providerName) {
        smartMeterService.switchProviderForSingleSmartMeter(smartMeterId, providerName);
    }

    @PutMapping("/{smartMeterId}/approve-or-reject/{status}/{providerName}")
    public void approveOrRejectSmartMeter(@PathVariable String smartMeterId, @PathVariable String status, @PathVariable String providerName) {
        smartMeterService.approveOrRejectSmartMeter(smartMeterId, status, providerName);
    }
}
