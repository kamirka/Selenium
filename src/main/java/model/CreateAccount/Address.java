package model.CreateAccount;

import lombok.Data;

@Data
public class Address {
    private String firstName;
    private String lastName;
    private String companyName;
    private String address1Name;
    private String address2Name;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String additionalInformation;
    private String homePhone;
    private String mobilePhone;
    private String addressAlias;
}
