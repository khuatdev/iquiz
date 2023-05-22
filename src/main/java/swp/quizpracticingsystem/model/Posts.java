package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Posts {
    @Id
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "post_category_id")
    private Integer postCategoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "brief_infor")
    private String briefInfor;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "featuring")
    private boolean featuring;
}
