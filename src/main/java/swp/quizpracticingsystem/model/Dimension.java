package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dimension")
public class Dimension {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "idSub")
    private Integer idSub;

    @Column(name = "dimension")
    private String dimension;

    @Column(name = "type")
    private String type;
}
