package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TEST")
public class Test {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @OneToOne
    private TestInfo testInfo;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<CheckedItem> checkedItems = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    public void setTestInfo(TestInfo testInfo){
        this.testInfo = testInfo;
        testInfo.setTest(this);
    }

    public void addCheckedItems(CheckedItem checkedItem){
        checkedItems.add(checkedItem);
        checkedItem.setTest(this);
    }
}
