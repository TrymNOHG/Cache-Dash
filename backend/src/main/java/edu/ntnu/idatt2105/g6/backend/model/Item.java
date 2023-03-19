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
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "item_id", nullable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @NonNull
    @ToString.Exclude
    private User user;

}
