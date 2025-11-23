package org.example.checker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.checker.model.invariants.EventStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subtitle;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(columnDefinition = "SMALLINT default 0")
    private Integer ageRestriction;

    private String coverImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organizer organizer;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "id")
    private List<EventSession> eventSessions;

    @ManyToMany(mappedBy = "favoriteEvents")
    private List<User> favoritedBy;// пользователи у которых избранном
}
