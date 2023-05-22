
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slider")
public class Slider {
    @Id
    @Column(name = "slider_id")
    private Integer sliderId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "backlink")
    private String backlink;

    @Column(name = "status")
    private String status;

    @Column(name = "visibility")
    private boolean visibility;

    @Column(name = "slider_category_id")
    private Integer sliderCategoryId;
}
