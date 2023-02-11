package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.service.SmartMeterService;

@RestController
@RequestMapping("smartmeter")
public class SmartMeterController {

    @Autowired
    SmartMeterService smartMeterService;

    @PostMapping
    public String createSmartMeter(@RequestBody SmartMeter smartMeter) {
        smartMeterService.createSmartMeter(smartMeter);
        return "Smart meter created";
    }
}
