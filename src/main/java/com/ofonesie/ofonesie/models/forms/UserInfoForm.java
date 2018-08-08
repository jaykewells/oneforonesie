package com.ofonesie.ofonesie.models.forms;

import com.ofonesie.ofonesie.models.Listing;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UserInfoForm {

        @NotNull
        @Size(min=3, max=50, message = "You must have a Username at least 3 characters long and shorter than 50 characters.")
        private String username;

        @NotNull
        @Size(min=3, max=50, message = "Must have a password at least 3 characters long.")
        private String password;

        @NotNull(message = "Must Verify your Password!")
        private String verify;

        @NotNull(message = "Must include a first name!")
        private String firstName;

        @NotNull(message = "Must include a last name!")
        private String lastName;

        @NotNull(message = "Must include a street address!")
        private String address1;

        @NotNull
        private String city;

        @NotNull
        private String state;

        @NotNull
        private Integer zip;

        @NotNull
        private String role;

        @OneToMany
        @JoinColumn(name="owner_id")
        private List<Listing> listings = new ArrayList<>(); //Storage of all current listings

        public UserInfoForm(){}
        public UserInfoForm(String username, String password, String firstName,
                        String lastName, String address1,
                        String city, String state, Integer zip) {
            this.username = username;
            this.password = password;
            this.role = "USER";
            this.firstName = firstName;
            this.lastName = lastName;
            this.address1 = address1;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String aName){
            this.username = aName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerify() {
        return verify;
    }

        public void setVerify(String verify) {
        this.verify = verify;
    }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Integer getZip() {
            return zip;
        }

        public void setZip(Integer zip) {
            this.zip = zip;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public List<Listing> getListings() {
            return listings;
        }

        public void setListings(List<Listing> listings) {
            this.listings = listings;
        }
    }
