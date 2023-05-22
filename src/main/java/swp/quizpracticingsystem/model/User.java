package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "Full")
    private String full;

    @Column(name = "Email")
    private String email;

    @Column(name = "Gender")
    private boolean gender;

    @Column(name = "Mobile")
    private Integer mobile;

    @Column(name = "Password")
    private String password;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "course_id")
    private Integer courseId;
}
