package com.klef.fsad.exam;

import javax.persistence.*;
import java.util.Date;

/**
 * Supplier entity mapped via Hibernate.
 */
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Column(nullable = false)
    private String status;

    // Required by Hibernate
    public Supplier() {
    }

    public Supplier(String name, String description, Date createdDate, String status) {
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                '}';
    }
}
