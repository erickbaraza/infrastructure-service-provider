package business;

import java.util.Date;

public class OrderItem {
   private long id;
   private long orderId;
   private int productId;
   private int quantity = 1;
   private float price;
   private float mrpPrice;
   private Date createdAt;
   private Date updatedAt;
   
   private byte[] image = null;
    
    private String imageToBase64 = null;
   
   private Order order;

    public OrderItem() {
    }

    public OrderItem(long id, long orderId, int productId, int quantity, float price, float mrpPrice, Date createdAt, Date updatedAt, String imageToBase64) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.mrpPrice = mrpPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageToBase64 = imageToBase64;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
      public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public String getImageToBase64() {
        return imageToBase64;
    }

    public void setImageToBase64(String imageToBase64) {
        this.imageToBase64 = imageToBase64;
    }
   
}
