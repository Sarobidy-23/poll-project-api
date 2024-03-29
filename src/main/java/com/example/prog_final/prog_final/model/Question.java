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
@Table(name = "question")
@Getter
@Setter
@ToString
@Builder
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String type;

    @ElementCollection
    @Column(name = "possible_response")
    private List<String> possible_response;

    private int order_in_poll;

    private boolean required;

    @CreationTimestamp
    private Instant creation_datetime;

    @ManyToOne
    @JoinColumn(name = "idPolls", nullable = true, insertable = true, updatable = true)
    private Poll poll;
}
