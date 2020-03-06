package tb.spring.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class DAOEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;
    @Column
    private String name;
    @Column
    @JsonIgnore
    private String designation;

    public long getEmpIdd() {
        return empId;
    }

    public void setEmpIdd(long empIdd) {
        this.empId = empIdd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
