package com.example.prog_final.prog_final.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@Transactional
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id_user;

    private String username;

    private String email;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name ="id_user")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Poll> poll = new HashSet<>();

    @JsonIgnore
    @OneToMany
    @JoinColumn(name ="id_user")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Response> responses = new HashSet<>();
}
