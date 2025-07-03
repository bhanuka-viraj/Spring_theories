package org.bhanuka.dto;

public class AddressDto {
    private String city;
    private int postalCode;
    private String province;

    public AddressDto(String city, int code, String province) {
        this.city = city;
        this.postalCode = code;
        this.province = province;
    }

    public AddressDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
