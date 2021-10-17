package ch9.system.pages;

import java.util.Objects;

public class OwnerInfo {
    private String name;
    private String address;
    private String city;
    private String telephone;
    private String pets;

    public OwnerInfo(String name, String address, String city, String telephone, String pets) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    public String getName() {
        return name;
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

    public String getPets() {
        return pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerInfo ownerInfo = (OwnerInfo) o;
        return Objects.equals(name, ownerInfo.name) &&
                Objects.equals(address, ownerInfo.address) &&
                Objects.equals(city, ownerInfo.city) &&
                Objects.equals(telephone, ownerInfo.telephone) &&
                Objects.equals(pets, ownerInfo.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, city, telephone, pets);
    }

    @Override
    public String toString() {
        return "OwnerInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets='" + pets + '\'' +
                '}';
    }
}
