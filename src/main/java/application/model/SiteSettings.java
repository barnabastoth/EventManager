package application.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SiteSettings")
public class SiteSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean leftBarOpen;
    private Boolean rightBarOpen;
    private Boolean eventBarOpen;
    private String serverEmail;
    private String companyPhone;

    @Column(length = 1024)
    private String contactPageText;
}
