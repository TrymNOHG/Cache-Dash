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
@Table(name = "key_infos")
public class KeyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_info_id", nullable = false)
    @NonNull
    private Long keyInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @ToString.Exclude
    private Item item;

    @Column(name = "title", nullable = false)
    @NonNull
    private String title;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;


}
