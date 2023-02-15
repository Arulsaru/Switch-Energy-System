package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.SmartMeterReading;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.repository.TotalReadingsRepository;

import java.util.List;

@Service
public class TotalReadingsService {

    @Autowired
    TotalReadingsRepository totalReadingsRepository;

    public List<TotalReadings> getAllReadingsBySmartMeterId(String smartMeterId) {
        return totalReadingsRepository.getAllReadingsBySmartMeterId(smartMeterId);
    }

    public void pushSmartMeterReadingsIntoTotalReadingList(SmartMeterReading smartMeterReading) {
        totalReadingsRepository.pushSmartMeterReadingsIntoTotalReadingList(smartMeterReading);
    }

//    public void calculateReadings(SmartMeterReading smartMeterReading, TotalReadings totalReadings) {
//        totalReadingsRepository.calculateReadings(smartMeterReading, totalReadings);
//    }
}
