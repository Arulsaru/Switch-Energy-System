package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.service.SmartMeterService;

@RestController
@RequestMapping("controller")
public class SmartMeterController {
    @Autowired
    SmartMeterService smartMeterService;
    @PostMapping
    public String createSmartMeter(@RequestBody SmartMeter smartMeter) {
        smartMeterService.createSmartMeter(smartMeter);
        return "Smart meter created";
    }
}
