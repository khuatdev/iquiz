package swp.quizpracticingsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "price_package")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PricePackage {
    @Id
    @Column(name = "idprice")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrice;

    @ManyToOne
    @JoinColumn(name="idsub", referencedColumnName = "idcourse")
    private Subject subject;

    @Column(name = "packagename")
    private String packageName;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Column(name = "saleprice")
    private Integer salePrice;

    @Column(name = "status")
    private Integer status;
}
