package switch_energy_system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import switch_energy_system.dto.SmartMeterReadingResponse;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.repository.ProviderRepository;
import switch_energy_system.repository.TotalReadingsRepository;

import java.util.List;

@Service
@EnableScheduling
public class TotalReadingsService {

    @Autowired
    TotalReadingsRepository totalReadingsRepository;

    @Autowired
    ProviderRepository providerRepository;

    private static final Logger log = LoggerFactory.getLogger(TotalReadingsService.class);

    public TotalReadings getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsRepository.getAllReadingsBySmartMeterId(smartMeterId);
    }

//    @Scheduled(cron = "*/10 * * * * *")
//    public void calculateAndStoreReading() {
//        totalReadingsRepository.calculateAndStoreReading();
//        log.info("reading stored");
//    }

    public List<SmartMeterReadingResponse> calculateTotalReadingsOfASmartMeter() {
        return totalReadingsRepository.calculateTotalReadingsOfASmartMeter();
    }

    public void updateTotalAmountForSmartMeter(String smartMeterId, String providerName) {
        double ratePerWatt = providerRepository.getProviderByProviderName(providerName).getRatePerWatt();
        int totalReading = calculateTotalReadingsOfASmartMeter().stream()
                .filter(readings -> readings.getId().equals(smartMeterId)).toList().get(0).getTotal();
        double amount = totalReading * ratePerWatt;
        totalReadingsRepository.updateTotalAmountForSmartMeter(smartMeterId, amount);
    }

//    public List<TotalReadings> calculateTotalReadingsOfASmartMeter(String smartMeterId) {
//        return totalReadingsRepository.calculateTotalReadingsOfASmartMeter(smartMeterId);
//    }
}
