/**
 * @Author Rasmus Berg
 */
package com.example.demo.Domain;

import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import org.apache.tomcat.util.codec.binary.Base64;

public class Customer implements DomainInterface {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int customer_id;
    private String name;
    private String contact_name;
    private String contact_email;
    private String contact_phone;
    private byte[] img;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Customer() {
    }

    public Customer(int customer_id, String name, String contact_name, String contact_email, String contact_phone, byte[] img) {
        this.customer_id = customer_id;
        this.name = name;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.img = img;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String byteArrayAs64String() {
        return Base64.encodeBase64String(this.img);
    }
}
