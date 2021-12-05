package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "TESTINFO")
public class TestInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String testname;

    @OneToOne(mappedBy = "testInfo")
    private Test test;
}
