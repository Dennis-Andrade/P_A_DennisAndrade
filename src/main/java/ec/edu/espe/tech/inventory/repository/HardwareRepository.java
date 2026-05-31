package ec.edu.espe.tech.inventory.repository;

import ec.edu.espe.tech.inventory.entity.HardwareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<HardwareEntity, Long> {
}
