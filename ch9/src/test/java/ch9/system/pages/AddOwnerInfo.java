package ch9.system.pages;

public class AddOwnerInfo {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    public AddOwnerInfo(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }

    public OwnerInfo toOwnerInfo() {
        return new OwnerInfo(firstName + " " + lastName, address, city, telephone, "");
    }
}
