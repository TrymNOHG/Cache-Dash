package edu.ntnu.idatt2105.g6.backend.dto;

import edu.ntnu.idatt2105.g6.backend.model.Message;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDTO {

    //TODO: test that messages are sorted by timestamp;
    @NonNull
    private List<Message> messages;
    private final String[] usersInvolved = new String[2];

    public ConversationDTO(@NonNull List<Message> messages, @NonNull String username1, @NonNull String username2) {
        this.messages = messages;
        usersInvolved[0] = username1;
        usersInvolved[1] = username2;
    }
}
