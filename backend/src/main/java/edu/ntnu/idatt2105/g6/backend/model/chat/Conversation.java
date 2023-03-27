package edu.ntnu.idatt2105.g6.backend.model.chat;

import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

/**

 This class represents a conversation between two users in the chat system of the e-commerce app.

 Each conversation has an ID and two users (user1 and user2) associated with it.

 The conversation also contains a list of messages exchanged between the users.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "conversations")
public class Conversation {

    /**
     The ID of the conversation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private Long conversationId;

    /**
     The first user in the conversation.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id1")
    @NonNull
    @ToString.Exclude
    private User user1;

    /**
     The second user in the conversation.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id2")
    @NonNull
    @ToString.Exclude
    private User user2;

    /**
     The list of messages exchanged between the users in the conversation.
     */
    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

}
