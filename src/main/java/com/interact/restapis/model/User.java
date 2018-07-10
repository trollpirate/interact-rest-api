package com.interact.restapis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Report.class)
    @JoinColumn(name = "to_user_id")
    private List<Report> reportReceivedList;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Report.class)
    @JoinColumn(name = "from_user_id")
    private List<Report> reportSentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Report> getReportReceivedList() {
        return reportReceivedList;
    }

    public void setReportReceivedList(List<Report> reportReceivedList) {
        this.reportReceivedList = reportReceivedList;
    }

    public List<Report> getReportSentList() {
        return reportSentList;
    }

    public void setReportSentList(List<Report> reportSentList) {
        this.reportSentList = reportSentList;
    }

}
