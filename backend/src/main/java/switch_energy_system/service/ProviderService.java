package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.Provider;
import switch_energy_system.repository.ProviderRepository;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    SmartMeterService smartMeterService;

    public void createProvider(Provider provider) {
        providerRepository.createProvider(provider);
    }

    public Provider getProviderByProviderName(String providerName) {
        return providerRepository.getProviderByProviderName(providerName);
    }

    public void enableProvider(String providerName) {
        providerRepository.enableOrDisableProvider(providerName, true);
    }

    public void disableProvider(String providerName) throws Exception {
        // should switch provider for all the users using this provider
        if(getAllEnabledProviders().toArray().length == 1) {
            throw new Exception("There should be atleast one provider in enable state");
        }
        providerRepository.enableOrDisableProvider(providerName, false);
        smartMeterService.switchProvidersForAllSmartMeters(providerName);
    }

    public List<Provider> getAllProviders(long pageNumber, long pageSize) {
        return providerRepository.getAllProviders(pageNumber, pageSize);
    }

    public void addSmartMeterToTheProviderList(String smartMeterId, String providerName) {
        providerRepository.addSmartMeterToTheProviderList(smartMeterId, providerName);
    }

    public void removeSmartMeterFromProviderList(String smartMeterId, String providerName) {
        providerRepository.removeSmartMeterFromProviderList(smartMeterId, providerName);
    }

    public List<Provider> getAllEnabledProviders() {
        return providerRepository.getAllEnabledProviders();
    }
}
