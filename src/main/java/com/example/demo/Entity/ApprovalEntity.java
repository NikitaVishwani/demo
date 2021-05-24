package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name="Approval")
public class ApprovalEntity {
    @Id
    @GeneratedValue
    private String id;

    @Column(name="subscription_id")
    private String subscription_id;

    @Column(name="status")
    private String status;

    @Column(name="update_req_id")
    private String update_req_id;
    //Setters and getters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_req_id() {
        return update_req_id;
    }

    public void setUpdate_req_id(String update_req_id) {
        this.update_req_id = update_req_id;
    }

    @Override
    public String toString() {
        return "SubscriptionEnitity [ id=" + id +
                ", subscription_id = " + subscription_id + ", status = " + status + " , update_req_id = " +
                update_req_id + "] ";

    }
}
