package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userprofile")
public class Userprofile {
    @Id
    @Column(name = "user_profile_id")
    private Integer userProfileId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "Headline")
    private String headline;
}
