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
public class ConversationDTO {

    //TODO: test that messages are sorted by timestamp;
    @NonNull
    private Message message;

    @NonNull
    private String username1;

    @NonNull
    private String username2;

}