package edu.ntnu.idatt2105.g6.backend.repo.chat;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**

 This repository provides CRUD operations for the Conversation entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    /**
     * Finds a conversation by its ID.
     *
     * @param conversationId The ID of the conversation to find.
     * @return An Optional containing the conversation, or an empty Optional if the conversation was not found.
     */
    Optional<Conversation> findByConversationId(Long conversationId);

    /**
     * Finds all conversations that involve a given user.
     *
     * @param user One of the users involved in the conversations.
     * @param user2 The other user involved in the conversations.
     * @return An Optional containing a List of all conversations involving the given user,
     *         or an empty Optional if no conversations were found.
     */
    Optional<List<Conversation>> findAllByUser1OrUser2(User user, User user2);

}
