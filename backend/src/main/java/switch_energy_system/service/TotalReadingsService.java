package switch_energy_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import switch_energy_system.dto.SmartMeterReadingResponse;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.repository.TotalReadingsRepository;
import switch_energy_system.repository.UserRepository;

import java.util.List;

@Service
@EnableScheduling
public class TotalReadingsService {

    @Autowired
    TotalReadingsRepository totalReadingsRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(TotalReadingsService.class);

    public TotalReadings getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsRepository.getAllReadingsBySmartMeterId(smartMeterId);
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void calculateAndStoreReading() {
        if(userRepository.getAllUser().toArray().length > 1) { // 0th user will be admin
            totalReadingsRepository.calculateAndStoreReading();
            log.info("reading stored");
        }
    }

    public List<SmartMeterReadingResponse> calculateTotalReadingsOfASmartMeter() {
        return totalReadingsRepository.calculateTotalReadingsOfASmartMeter();
    }

    public long getReadingsOfASmartMeterById(String smartMeterId) {
        return totalReadingsRepository.calculateTotalReadingsOfASmartMeter()
                .stream()
                .filter(smartMeters -> smartMeters.getId().equals(smartMeterId))
                .toList()
                .get(0)
                .getTotal();
    }
}
