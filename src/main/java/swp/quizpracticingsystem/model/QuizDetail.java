package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_detail")
public class QuizDetail {
    @Id
    @Column(name = "idQuiz")
    private Integer idQuiz;

    @Column(name = "id_Lesson")
    private Integer idLesson;
}
