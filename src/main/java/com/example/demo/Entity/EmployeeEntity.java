package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BUSINESS_PROFILE")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(name="company_name")
    private String companyname;

    @Column(name="legal_name")
    private String legalname;

    @Column(name="business_address")
    private String businessaddress;

    @Column(name="legal_address")
    private String legaladdress;

    @Column(name="tax_identifiers")
    private String tax_identifiers;

    @Column(name="email", nullable=false, length=200)
    private String email;

    @Column(name="website", nullable=false, length=200)
    private String website;


    //Setters and getters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getBusinessaddress() {
        return businessaddress;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public String getLegaladdress() {
        return legaladdress;
    }

    public void setLegaladdress(String legaladdress) {
        this.legaladdress = legaladdress;
    }

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname;
    }

    public String getTax_identifiers() {
        return tax_identifiers;
    }

    public void setTax_identifiers(String tax_identifiers) {
        this.tax_identifiers = tax_identifiers;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeEntity [ companyname=" + companyname +
                ", legalname = " + legalname +
        ", businessaddress = " + businessaddress + " , legaladdress = " + legaladdress + " ,tax_identifiers =" + tax_identifiers  + ", email = " + email  + ", website " + website + "] ";

    }


}
