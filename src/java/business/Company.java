package business;

import java.util.Date;

/**
 *
 * @author ERICK
 */
public class Company {
    private String id;
    private String regNo;
    private String name;
    private String description;
    private String email;
    private String phone;
    private String address;
    private long categoryId;
    private String website;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    
    private byte[] image = null;
    
    private String imageToBase64 = null;

    private Category category;
    
    public Company() {
    }

    public Company(String id, String regNo, String name, String description, String email, String phone, String address, long categoryId, String website, Date createdAt, Date updatedAt) {
        this.id = id;
        this.regNo = regNo;
        this.name = name;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.categoryId = categoryId;
        this.website = website;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
    
    public void setCategory( Category category){
        this.category = category;
    }
    
    public Category getCategory(){
        return category;
    }
}
