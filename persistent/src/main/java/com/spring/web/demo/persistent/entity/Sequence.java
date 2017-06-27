package com.spring.web.demo.persistent.entity;

import javax.persistence.*;
import java.time.*;

@Entity
public class Sequence {

    @Id
    @Column(name = "sequence_id", nullable = false)
    public String sequenceId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User user;
    @Column(name = "expired_date", nullable = false)
    public Instant expiredDate;

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }
}
