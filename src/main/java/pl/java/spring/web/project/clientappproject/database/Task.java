package pl.java.spring.web.project.clientappproject.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;

    private String description;

    private BigDecimal price;

    private int priority;

    @ManyToMany(mappedBy = "tasks")
        private Collection<Employee> employees = new ArrayList<>();

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
