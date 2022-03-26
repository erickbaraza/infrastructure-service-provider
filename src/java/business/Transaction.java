package business;

import java.util.Date;

public class Transaction {
    private long id;
    private long userId;
    private String mpesaRef;
    private long mpesaNumber;
    private float amount;
    private long orderId;
    private Date createdAt;
    private Date updatedAt;

   
    public Transaction() {
    }

    public Transaction(long id, long userId, long orderId, String mpesaRef, long mpesaNumber, float amount, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.mpesaRef = mpesaRef;
        this.mpesaNumber = mpesaNumber;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    
    public String getMpesaRef() {
        return mpesaRef;
    }

    public void setMpesaRef(String mpesaRef) {
        this.mpesaRef = mpesaRef;
    }

    public long getMpesaNumber() {
        return mpesaNumber;
    }

    public void setMpesaNumber(long mpesaNumber) {
        this.mpesaNumber = mpesaNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
   
   
   
}
