package edu.ntnu.idatt2105.g6.backend.repo;

import edu.ntnu.idatt2105.g6.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
