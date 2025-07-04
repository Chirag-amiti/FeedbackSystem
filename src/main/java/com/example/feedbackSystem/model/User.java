package com.example.feedbackSystem.model;

import jakarta.persistence.*;

// import java.util.UUID; // add this for uuid

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // @Id
    // @GeneratedValue
    // @Column(columnDefinition = "BINARY(16)") //CHAR(36)
    // private UUID id;


    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String team;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "cycle_id") // foreign key column in the users table
    private PerformanceCycle performanceCycle;

    public User() {
    }

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    //  public UUID getId() {
    //     return id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public PerformanceCycle getPerformanceCycle() {
        return performanceCycle;
    }

    public void setPerformanceCycle(PerformanceCycle performanceCycle) {
        this.performanceCycle = performanceCycle;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
