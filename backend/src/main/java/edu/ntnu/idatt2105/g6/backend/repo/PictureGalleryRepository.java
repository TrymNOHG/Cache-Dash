package edu.ntnu.idatt2105.g6.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureGalleryRepository extends JpaRepository<PictureGalleryRepository, Long> {
}
