package mstokluska.testing.account;

public class Account {

    private boolean active;
    private Address defaultDeliveryAddress;
    private String mail;

    public Account() {
        this.active = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if (defaultDeliveryAddress != null) {
            activate();
        } else {
            this.active = false;
        }
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }

    public void setMail(String mail) {

        if (mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.mail = mail;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

    public String getMail() {
        return mail;
    }
}
