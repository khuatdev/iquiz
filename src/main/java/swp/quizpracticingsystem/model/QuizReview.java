package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_review")
public class QuizReview {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "idQuiz")
    private Integer idQuiz;

    @Column(name = "score")
    private Integer score;

    @Column(name = "answer")
    private boolean answer;
}
