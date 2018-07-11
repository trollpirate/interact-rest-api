package com.interact.restapis.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/* TODO Add LOCALE support for DATE objects. Priority - LOW */

@Entity
@Table (name = "report")
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_user_id", nullable = false)
    private Long fromUserId;

    @Column(name = "to_user_id", nullable = false)
    private Long toUserId;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    private String observation;

    @Column(nullable = false)
    private String context;

    private String recommendation;

    @Column(nullable = false)
    private String eventName;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(nullable = false)
    private Date eventDate;

    private boolean isAnonymous;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date acknowledgementDate;

    private boolean isAcknowledged;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Date getAcknowledgementDate() {
        return acknowledgementDate;
    }

    public void setAcknowledgementDate(Date acknowledgementDate) {
        this.acknowledgementDate = acknowledgementDate;
    }

    public boolean isAcknowledged() {
        return isAcknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        isAcknowledged = acknowledged;
    }
}
