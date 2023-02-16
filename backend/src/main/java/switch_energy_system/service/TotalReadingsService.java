package switch_energy_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.SmartMeterReading;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.repository.TotalReadingsRepository;

import java.util.List;

@Service
@EnableScheduling
public class TotalReadingsService {

    @Autowired
    TotalReadingsRepository totalReadingsRepository;
    private static final Logger log = LoggerFactory.getLogger(TotalReadingsService.class);

    public TotalReadings getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsRepository.getAllReadingsBySmartMeterId(smartMeterId);
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void calculateAndStoreReading() {
        totalReadingsRepository.calculateAndStoreReading();
        log.info("working");
    }

//    public List<TotalReadings> calculateTotalReadingsOfASmartMeter(String smartMeterId) {
//        return totalReadingsRepository.calculateTotalReadingsOfASmartMeter(smartMeterId);
//    }
}
