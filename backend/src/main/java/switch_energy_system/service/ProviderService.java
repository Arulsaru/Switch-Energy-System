package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.Provider;
import switch_energy_system.repository.ProviderRepository;

@Service
public class ProviderService {
    @Autowired
    ProviderRepository providerRepository;
    public void createProvider(Provider provider) {
        providerRepository.createProvider(provider);
    }
    public void enableProvider(String providerName) {
        providerRepository.enableOrDisableProvider(providerName, true);
    }
    public void disableProvider(String providerName) {
        // should switch provider for all the users using this provider
        providerRepository.enableOrDisableProvider(providerName, false);
    }
}
