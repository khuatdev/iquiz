package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
