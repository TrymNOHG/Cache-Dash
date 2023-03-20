package edu.ntnu.idatt2105.g6.backend.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @NonNull
    private Long conversationId;

    @NonNull
    private String username;

    @NonNull
    private String message;

    @NonNull
    private Timestamp timestamp;
}
