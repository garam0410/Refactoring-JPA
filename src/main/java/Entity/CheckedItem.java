package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CHECKEDITEM")
public class CheckedItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Test test;

    @Column
    private int itemnumber;

    @Column
    private int itemresult;
}
