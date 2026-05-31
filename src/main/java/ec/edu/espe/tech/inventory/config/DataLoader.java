package ec.edu.espe.tech.inventory.config;

import ec.edu.espe.tech.inventory.entity.HardwareEntity;
import ec.edu.espe.tech.inventory.enums.HardwareCategory;
import ec.edu.espe.tech.inventory.enums.HardwareStatus;
import ec.edu.espe.tech.inventory.repository.HardwareRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final HardwareRepository hardwareRepository;

    public DataLoader(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public void run(String... args) {
        if (hardwareRepository.count() > 0) {
            return;
        }

        List<HardwareEntity> hardwareList = new ArrayList<>();
        HardwareCategory[] categories = HardwareCategory.values();

        for (int i = 1; i <= 10000; i++) {
            HardwareCategory category = categories[i % categories.length];
            HardwareStatus status = i % 5 == 0 ? HardwareStatus.DEBAJA : HardwareStatus.ACTIVO;
            BigDecimal price = BigDecimal.valueOf(500 + (i % 3500));
            LocalDate purchaseDate = LocalDate.now().minusMonths(i % 96);
            String model = category.name() + "-" + i;

            hardwareList.add(new HardwareEntity(model, category, price, purchaseDate, status));
        }

        hardwareRepository.saveAll(hardwareList);
    }
}
