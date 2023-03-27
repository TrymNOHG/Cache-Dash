package edu.ntnu.idatt2105.g6.backend.model.chat;

import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;

/**

 This class represents a message in a conversation between two users. Each message has a unique ID and is associated
 with a conversation and a sender. The message content and the time it was sent are also stored.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    /**
     * Unique identifier for this message.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    /**
     * The conversation that this message belongs to.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    @ToString.Exclude
    private Conversation conversation;

    /**
     * The user who sent this message.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @NonNull
    @ToString.Exclude
    private User sender;

    /**
     * The content of the message.
     */
    @Column(name = "message", nullable = false)
    @NonNull
    private String message;

    /**
     * The timestamp of when this message was sent.
     */
    @Column(name = "time_sent", nullable = false)
    @NonNull
    private Timestamp timestamp;

}
