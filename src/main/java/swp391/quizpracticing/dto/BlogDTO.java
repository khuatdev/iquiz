package swp391.quizpracticing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.quizpracticing.model.Blog;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Integer id;
    private String title;
    private String content;
    private String thumbnail;
    private String briefInfo;
    private Date lastUpdated;
    private String status;
    private Boolean featuring;
    private List<CategoryDTO>categories;
    private UserDTO author;

    public void blogHomePage(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.thumbnail = blog.getThumbnail();
        this.briefInfo = blog.getBriefInfo();
        this.lastUpdated = blog.getLastUpdated();
        this.status = blog.getStatus();
        this.featuring = blog.getFeaturing();
//        BlogcategoryDTO blogCategoryDTO = new BlogcategoryDTO();
//        blogCategoryDTO.entityToDTO((Blogcategory) blog.getCategories());
//        this.categories = (List<CategoryDTO>) blogCategoryDTO;
        UserDTO userDTO = new UserDTO();
        userDTO.entityToDTO(blog.getAuthor());
        this.author = userDTO;
    }
}
