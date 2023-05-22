package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject_detail")
public class SubjectDetail {
    @Id
    @Column(name = "idSub")
    private Integer idSub;

    @Column(name = "id_course")
    private Integer idCourse;
}
