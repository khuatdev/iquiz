package swp.quizpracticingsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "price_package")
public class PricePackage {
    @Id
    @Column(name = "idPrice")
    private Integer idPrice;

    @Column(name = "idSub")
    private Integer idSub;

    @Column(name = "packageName")
    private Integer packageName;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Column(name = "salePrice")
    private Integer salePrice;

    @Column(name = "status")
    private Integer status;
}
