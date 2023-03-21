package edu.ntnu.idatt2105.g6.backend.dto.chat;

import edu.ntnu.idatt2105.g6.backend.model.chat.Message;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationLoadDTO {

    //TODO: test that messages are sorted by timestamp;
    @NonNull
    private List<Message> messages;

    @NonNull
    private String username1;

    @NonNull
    private String username2;

}
