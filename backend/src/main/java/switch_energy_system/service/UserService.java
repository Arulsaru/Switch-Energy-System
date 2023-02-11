package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.Provider;
import switch_energy_system.pojo.User;
import switch_energy_system.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.createUser(user);
    }

    public void enableProvider(String providerName) {
        userRepository.enableOrDisableProvider(providerName, true);
    }

    public void disableProvider(String providerName) {
        // should switch provider for all the users using this provider
        switchProvidersForAllSmartMeters(providerName, "new provider name");
        userRepository.enableOrDisableProvider(providerName, false);
    }

    public List<Provider> getAllproviders() {
        return userRepository.getAllProviders();
    }

    public void switchProviderForSingleSmartMeter(String smartMeterId, String providerName) {
        userRepository.switchProviderForSingleSmartMeter(smartMeterId, providerName);
    }

    public void switchProvidersForAllSmartMeters(String oldProviderName, String newProviderName) {
        userRepository.switchProvidersForAllSmartMeters(oldProviderName, newProviderName);
    }

    public void approveOrRejectSmartMeter(String smartMeterId, String status) {
        userRepository.approveOrRejectSmartMeter(smartMeterId, status);
    }
}
