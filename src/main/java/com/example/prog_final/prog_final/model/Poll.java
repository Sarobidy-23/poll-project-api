package com.example.prog_final.prog_final.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poll")
@Getter
@Setter
@ToString
@Builder
@Transactional
public class Poll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPoll;

    private String title;

    @CreationTimestamp
    private Instant creationDatetime;

    private int idOwner;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true, insertable = true, updatable = true)
    private User user;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name ="idPoll")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Question> questions = new HashSet<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name ="idPoll")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Response> responses = new HashSet<>();
}
