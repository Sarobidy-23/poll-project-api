package com.example.prog_final.prog_final.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "response")
@Getter
@Setter
@ToString
@Builder
public class Response implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResponse;

    private String question;

    @ElementCollection
    @Column(name = "choose_response")
    private List<String> chooseResponse;

    private int idPoll;

    @ManyToOne
    @JoinColumn(name = "idPolls", nullable = true, insertable = true, updatable = true)
    private Poll poll;


    private int idOwner;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = true, insertable = true, updatable = true)
    private Users users;

    @CreationTimestamp
    private Instant choiceDatetime;
}
