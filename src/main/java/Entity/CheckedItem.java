package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "CHECKEDITEM")
@SequenceGenerator(
        name = "CHECKEDITEM_SEQ_GENERATOR",
        sequenceName = "CHECKEDITEM_SEQ", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 187
)
public class CheckedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKEDITEM_SEQ_GENERATOR")
    private Long id;

    @ManyToOne
    private Test test;

    @Column
    private int itemnumber;

    @Column
    private int itemresult;
}
