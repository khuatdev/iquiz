package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "idCourse")
    private Integer idCourse;

    @Column(name = "idCategory")
    private Integer idCategory;

    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "dateStart")
    private LocalDate dateStart;
}
