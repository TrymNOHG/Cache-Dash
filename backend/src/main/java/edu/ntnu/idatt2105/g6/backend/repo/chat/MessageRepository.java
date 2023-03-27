package edu.ntnu.idatt2105.g6.backend.repo.chat;

import edu.ntnu.idatt2105.g6.backend.model.chat.Conversation;
import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**

 This repository provides CRUD operations for the Message entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     Retrieves all messages for a given conversation.

     @param conversation the conversation to retrieve messages for
     @return an optional list of messages for the conversation
     */
    Optional<List<Message>> findAllByConversation(Conversation conversation);
}
