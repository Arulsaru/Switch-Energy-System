package switch_energy_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<TotalReadings> getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsService.getAllReadingsBySmartMeterId(smartMeterId);
    }
}
