package edu.ntnu.idatt2105.g6.backend.repo;

import edu.ntnu.idatt2105.g6.backend.model.KeyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyInfoRepository extends JpaRepository<KeyInfo, Long> {
}
