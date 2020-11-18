package org.github.yibing.spring.ioc.domain;

import org.github.yibing.spring.ioc.annotation.Super;

@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{} " + super.toString();
    }
}
