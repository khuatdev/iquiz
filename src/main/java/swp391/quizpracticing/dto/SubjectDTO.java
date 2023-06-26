package swp391.quizpracticing.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Integer id;
    private String title;
    private String briefInfo;
    private String description;
    private String thumbnail;
    private Boolean status;
    private Date createdTime;
    private Date lastUpdatedTime;
    private UserDTO owner;
    private DimensionDTO dimension;
    private Boolean featured;
    private String tagLine;

}

