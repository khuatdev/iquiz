package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_category")
public class PostCategory {
    @Id
    @Column(name = "post_category_id")
    private Integer postCategoryId;

    @Column(name = "post_category")
    private String postCategory;

    @Column(name = "post_category_description")
    private String postCategoryDescription;
}
