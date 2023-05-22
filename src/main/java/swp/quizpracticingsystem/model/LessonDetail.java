package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lesson_detail")
public class LessonDetail {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "lesson_type")
    private String lessonType;

    @Column(name = "topic")
    private String topic;

    @Column(name = "lesson_order")
    private Integer lessonOrder;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "content")
    private String content;
}
