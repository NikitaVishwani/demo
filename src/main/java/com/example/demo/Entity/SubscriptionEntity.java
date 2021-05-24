package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name="Subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue
    private String id;

    @Column(name="subscription_id")
    private String subscription_id;


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

    @Override
    public String toString() {
        return "SubscriptionEntity [ id=" + id +
                ", subscription_id = " + subscription_id + "] ";

    }

}
