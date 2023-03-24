package edu.ntnu.idatt2105.g6.backend.model.chat;

import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @ToString.Exclude
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NonNull
    @ToString.Exclude
    private User sender;

    @Column(name = "message", nullable = false)
    @NonNull
    private String message;

    @Column(name = "time_sent", nullable = false)
    @NonNull
    private Timestamp timestamp;

}
