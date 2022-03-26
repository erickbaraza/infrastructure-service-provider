package business;

import java.util.Date;


public class Product {
    private long id;
    private String name;
    private String slug;
    private String shortDescription;
    private String description;
    private int quantity;
    private float price;
    private float mrpPrice;
    private long categoryId;
    private long companyId;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    
    private byte[] image = null;
    
    private String imageToBase64 = null;
    
    private Category category;
    
    private Company company;
    
    
    public Product() {
    }

    public Product(long id, String name, String slug, String shortDescription, String description, int quantity, float price, float mrpPrice, long categoryId, long companyId, String imageToBase64, String status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.mrpPrice = mrpPrice;
        this.categoryId = categoryId;
        this.companyId = companyId;
        this.imageToBase64 = imageToBase64;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }    

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageToBase64() {
        return imageToBase64;
    }

    public void setImageToBase64(String imageToBase64) {
        this.imageToBase64 = imageToBase64;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    
}
