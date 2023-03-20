package edu.ntnu.idatt2105.g6.backend.dto;

import edu.ntnu.idatt2105.g6.backend.model.Message;
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
    private List<Message> messages;

    @NonNull
    private String username1;

    @NonNull
    private String username2;

}
