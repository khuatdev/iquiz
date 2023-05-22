package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "setting")
public class Setting {
    @Id
    @Column(name = "idQuiz")
    private Integer idQuiz;

    @Column(name = "number_of_quiz")
    private Integer numberOfQuiz;

    @Column(name = "quiz_type")
    private String quizType;
}
