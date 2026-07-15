package com.zhaowa.galaxy.springcloud.customer.entity;

public class Customer {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
        this.email = "default@163.com";
        this.phone = "135-555-1234";
        this.address = "default address";
        this.city = "default city";
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
