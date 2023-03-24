package edu.ntnu.idatt2105.g6.backend.exception.not_found;

public class ConversationNotFoundException extends NotFoundException{
    public ConversationNotFoundException(Long value) {
        super("Conversation", value);
    }

    public ConversationNotFoundException(String value) {
        super("Conversation", value);
    }
}
