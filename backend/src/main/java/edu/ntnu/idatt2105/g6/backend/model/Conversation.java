package edu.ntnu.idatt2105.g6.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", nullable = false)
    @NonNull
    private Long conversationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @NonNull
    @ToString.Exclude
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @NonNull
    @ToString.Exclude
    private User user2;

}
