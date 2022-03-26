package business;

import java.util.Date;

public class Cart {
   private long id;
   private long userId;
   private int productId;
   private int quantity = 1;
   private float price;
   private float mrpPrice;
   private Date createdAt;
   private Date updatedAt;
   private boolean confirm = false;
   
   private User user;
   
   private Product product;
   
    public Cart() {
    }

    public Cart(long id, long userId, int productId, float price, float mrpPrice, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.mrpPrice = mrpPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    
    public Cart(long id, long userId, int productId, int quantity, float price, float mrpPrice, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.mrpPrice = mrpPrice;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(float mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
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
   
    public float getItemSubTotal(){
        if ( this.getMrpPrice() > 0){
            return this.getMrpPrice() * this.getQuantity();
        }
        return this.getPrice() * this.getQuantity();
    }
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
