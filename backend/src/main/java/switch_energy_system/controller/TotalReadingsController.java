package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import switch_energy_system.dto.SmartMeterReadingResponse;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.service.TotalReadingsService;

import java.util.List;

@RestController
@RequestMapping("readings")
@CrossOrigin("*")
public class TotalReadingsController {

    @Autowired
    TotalReadingsService totalReadingsService;

    @GetMapping("/{smartMeterId}")
    public TotalReadings getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsService.getAllReadingsBySmartMeterId(smartMeterId);
    }

    @GetMapping("/calculate")
    public List<SmartMeterReadingResponse> calculateTotalReadingsOfASmartMeter() {
        return totalReadingsService.calculateTotalReadingsOfASmartMeter();
    }

    @GetMapping("/smart-meter/{smartMeterId}")
    public long getReadingsOfASmartMeterById(@PathVariable String smartMeterId) {
        return totalReadingsService.getReadingsOfASmartMeterById(smartMeterId);
    }
}
