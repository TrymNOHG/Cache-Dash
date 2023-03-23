package edu.ntnu.idatt2105.g6.backend.model.chat;

import edu.ntnu.idatt2105.g6.backend.model.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id")
    private Long conversationId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id1")
    @NonNull
    @ToString.Exclude
    private User user1;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id2")
    @NonNull
    @ToString.Exclude
    private User user2;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @NonNull
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

}
