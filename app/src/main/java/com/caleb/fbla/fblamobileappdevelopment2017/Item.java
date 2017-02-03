package com.caleb.fbla.fblamobileappdevelopment2017;

/**
 * Created by User on 1/31/2017.
 */

public class Item {

    private String pictureLocation;
    private String title;
    private String price;
    private String description;
    private String firstName;
    private String lastName;
    private String email;
    private String cellPhone;

    public Item(){

    }

    public Item(String PictureLocation,String Title, String Price, String Description, String FirstName, String LastName, String Email, String CellPhone){
        pictureLocation = PictureLocation;
        title = Title;
        price = Price;
        description = Description;
        firstName = FirstName;
        lastName = LastName;
        email = Email;
        cellPhone = CellPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
