package business;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private long id;
    private long userId;
    private int items;
    private float amount;
    private float mrpAmount;
    private String status = "pending";
    private long paymentId;
    private Date createdAt;
    private Date updatedAt;

    private ArrayList<OrderItem> orderItems = null;

    private User user;
   
    public Order() {
    }

    public Order(long id, long userId, int items, float amount, float mrpAmount,Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.amount = amount;
        this.mrpAmount = mrpAmount;        
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
   
   public Order(long id, long userId, int items, float amount, float mrpAmount, long paymentId, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.amount = amount;
        this.mrpAmount = mrpAmount;
        this.status = status;
        this.paymentId = paymentId;
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

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getMrpAmount() {
        return mrpAmount;
    }

    public void setMrpAmount(float mrpAmount) {
        this.mrpAmount = mrpAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
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

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    
}
