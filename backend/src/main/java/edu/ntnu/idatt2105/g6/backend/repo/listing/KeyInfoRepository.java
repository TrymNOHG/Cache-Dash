package edu.ntnu.idatt2105.g6.backend.repo.listing;

import edu.ntnu.idatt2105.g6.backend.model.listing.KeyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyInfoRepository extends JpaRepository<KeyInfo, Long> {
}
