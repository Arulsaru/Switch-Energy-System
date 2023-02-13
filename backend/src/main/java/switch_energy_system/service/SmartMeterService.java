package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.repository.ProviderRepository;
import switch_energy_system.repository.SmartMeterRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartMeterService {
    @Autowired
    SmartMeterRepository smartMeterRepository;

    @Autowired
    ProviderRepository providerService;

    public void createSmartMeter(SmartMeter smartMeter) {
        smartMeterRepository.createSmartMeter(smartMeter);
    }

    public void switchProviderForSingleSmartMeter(String smartMeterId, String providerName) {
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
}
