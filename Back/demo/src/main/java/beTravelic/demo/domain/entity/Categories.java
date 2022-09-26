package beTravelic.demo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long category_id;

    @Column(name = "category_name")
    private String category_name;

    @Column(name = "content_type_id")
    private Integer content_type_id;

//    @Setter
//    @OneToOne(mappedBy = "categories")
//    private List<Place> place = new ArrayList<>();
//
//    @Setter
//    @OneToOne(mappedBy = "categories")
//    private List<UserCategories> userCategories = new ArrayList<>();
}