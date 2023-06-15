package swp391.quizpracticing.model;


import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id",referencedColumnName = "id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_package_id", referencedColumnName = "id")
    private Pricepackage pricePackage;

    @Column(name = "registration_time")
    private Timestamp registrationTime;

    @Column(name = "valid_from")
    private Timestamp validFrom;

    @Column(name = "valid_to")
    private Timestamp validTo;

    @Column(name="notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="last_updated_by", referencedColumnName = "id")
    private User userUpdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_status_id", referencedColumnName = "id")
    private Registrationstatus registrationStatus;
}
