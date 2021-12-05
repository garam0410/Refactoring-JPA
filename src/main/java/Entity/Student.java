package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Test> tests=new ArrayList<>();

    public void addTest(Test t){
        tests.add(t);
        t.setStudent(this);
    }
}
