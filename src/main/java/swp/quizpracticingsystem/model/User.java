package swp.quizpracticingsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Gender")
    private Boolean gender;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Password")
    private String password;

    @Column(name = "token", nullable = true)
    private String token;

    @Column(name = "lastupdatedatetime", nullable = true)
    private String lastupdatedatetime;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;


    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "role_id")
    private Role role;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Usercourse",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "idcourse")})
    private Set<Subject> subjects = new HashSet<>();

    public void token(String token) {
    }
}
