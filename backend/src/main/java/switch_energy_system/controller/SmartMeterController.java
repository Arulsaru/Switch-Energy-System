package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.SmartMeterReading;
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

    @PostMapping("/{userName}/create/{providerName}")
    public void createSmartMeter(@PathVariable String userName, @PathVariable String providerName) {
        smartMeterService.createSmartMeter(userName, providerName);
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

    @GetMapping("/{userName}")
    public List<SmartMeter> getAllApprovedSmartMeterByUserName(@PathVariable String userName) {
        return smartMeterService.getAllApprovedSmartMeterByUserName(userName);
    }

    @GetMapping("/readings/{smartMeterId}")
    public SmartMeterReading getLastSmartMeterReading(@PathVariable String smartMeterId) {
        return smartMeterService.getLastSmartMeterReading(smartMeterId);
    }
}
