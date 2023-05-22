package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_overview")
public class QuizOverview {
    @Id
    @Column(name = "idQuiz")
    private Integer idQuiz;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "level")
    private String level;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "pass_rate")
    private Integer passRate;

    @Column(name = "quiz_type")
    private String quizType;

    @Column(name = "description")
    private String description;
}
