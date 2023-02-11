package switch_energy_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch_energy_system.pojo.SmartMeter;
import switch_energy_system.repository.SmartMeterRepository;

@Service
public class SmartMeterService {
    @Autowired
    SmartMeterRepository smartMeterRepository;

    public void createSmartMeter(SmartMeter smartMeter) {
        smartMeterRepository.createSmartMeter(smartMeter);
    }
}
