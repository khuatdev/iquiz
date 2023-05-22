package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @Column(name = "idLesson")
    private Integer idLesson;

    @Column(name = "idCourse")
    private Integer idCourse;

    @Column(name = "lesson")
    private String lesson;

    @Column(name = "lesson_order")
    private Integer lessonOrder;

    @Column(name = "lesson_type")
    private String lessonType;

    @Column(name = "status")
    private String status;

    @Column(name = "action")
    private String action;
}
