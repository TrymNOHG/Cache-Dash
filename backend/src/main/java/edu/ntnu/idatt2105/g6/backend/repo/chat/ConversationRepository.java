package edu.ntnu.idatt2105.g6.backend.repo.chat;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByConversationId(Long conversationId);

    Optional<List<Conversation>> findAllByUser1OrUser2(User user, User user2);

}
