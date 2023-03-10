package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.service.SmartMeterService;

import java.util.List;

@RestController
@RequestMapping("smartmeter")
@CrossOrigin("*")
public class SmartMeterController {

    @Autowired
    SmartMeterService smartMeterService;

    @PostMapping("/{userName}/create/{providerName}")
    public void createSmartMeter(@PathVariable String userName, @PathVariable String providerName) {
        smartMeterService.createSmartMeter(userName, providerName);
    }

    @GetMapping
    public List<SmartMeter> getAllPendingSmartMeter(@RequestParam long pageNumber, @RequestParam long pageSize) {
        return smartMeterService.getAllPendingSmartMeter(pageNumber, pageSize);
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

    @GetMapping("{smartMeterId}/calculate-amount/{providerName}")
    public double calculateAmount(@PathVariable String smartMeterId, @PathVariable String providerName) {
        return smartMeterService.calculateAmount(smartMeterId, providerName);
    }
}
