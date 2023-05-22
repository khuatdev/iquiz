
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject_overview")
public class SubjectOverview {
    @Id
    @Column(name = "idOverview")
    private Integer idOverview;

    @Column(name = "idSub")
    private Integer idSub;

    @Column(name = "status")
    private boolean status;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;
}
