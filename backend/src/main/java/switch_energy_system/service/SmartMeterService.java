package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.pojo.SmartMeterReading;
import switch_energy_system.pojo.TotalReadings;
import switch_energy_system.repository.ProviderRepository;
import switch_energy_system.repository.SmartMeterRepository;
import switch_energy_system.repository.TotalReadingsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartMeterService {
    @Autowired
    SmartMeterRepository smartMeterRepository;

    @Autowired
    TotalReadingsRepository totalReadingsRepository;

    @Autowired
    ProviderRepository providerService;

    @Autowired
    TotalReadingsService totalReadingsService;

    public void createSmartMeter(String userName, String providerName) {
        SmartMeter smartMeter = new SmartMeter(userName);
        smartMeter.setProviderName(providerName);
        smartMeterRepository.createSmartMeter(smartMeter);

        SmartMeterReading smartMeterReading = new SmartMeterReading(0);
        TotalReadings totalReadings = new TotalReadings(smartMeter.getSmartMeterId());
        totalReadingsRepository.createTotalReadingsCollection(totalReadings);
        totalReadingsRepository.pushSmartMeterReadingsIntoTotalReadingList(smartMeterReading);  // initial
//        smartMeterRepository.createSmartMeterReading(new SmartMeterReading(smartMeter.getSmartMeterId()));
    }

    public void switchProviderForSingleSmartMeter(String smartMeterId, String providerName) {
        totalReadingsService.updateTotalAmountForSmartMeter(smartMeterId, providerName);
        smartMeterRepository.switchProviderForSingleSmartMeter(smartMeterId, providerName);
    }

    public void switchProvidersForAllSmartMeters(String oldProviderName) {
        String newProviderName = providerService.getAllProviders()
                        .stream()
                        .filter(Provider::isEnabled)
                        .toList().get(0).getProviderName();
        smartMeterRepository.switchProvidersForAllSmartMeters(oldProviderName, newProviderName);
    }

    public void approveOrRejectSmartMeter(String smartMeterId, String status, String providerName) {
        smartMeterRepository.approveOrRejectSmartMeter(smartMeterId, status);
        if(status.equals("APPROVED")) {
            providerService.addSmartMeterToTheProviderList(smartMeterId, providerName);
        }
    }

    public List<SmartMeter> getAllPendingSmartMeter() {
        return smartMeterRepository.getAllPendingSmartMeter()
                .stream().filter(smartMeter -> smartMeter.getSmartMeterStatus().equals("PENDING") && smartMeter.isEnabled())
                .collect(Collectors.toList());
    }

    public List<SmartMeter> getAllApprovedSmartMeterByUserName(String userName) {
        return smartMeterRepository.getAllApprovedSmartMeterByUserName(userName);
    }
}
