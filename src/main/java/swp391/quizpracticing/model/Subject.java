package swp391.quizpracticing.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "brief_info")
    private String briefInfo;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dimension_id", referencedColumnName = "id")
    private Dimension dimension;

    @OneToMany(mappedBy = "subjects")
    private List<Pricepackage> pricepackages;

    @OneToMany(mappedBy = "subject")
    private List<UserSubject> userSubjects;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subject_subcategory",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
    private List<Subcategory> subCategories;

    @OneToMany(mappedBy = "subject")
    private List<Lesson> lessons;

    @Column(name = "featured")
    private Boolean featured;

    @Column(name = "tagline")
    private String tagLine;
}
