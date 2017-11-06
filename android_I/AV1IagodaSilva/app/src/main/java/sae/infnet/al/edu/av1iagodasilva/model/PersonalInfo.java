package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by igMoreira on 05/11/17.
 */

public class PersonalInfo implements Parcelable {
    private String name;
    private String address;
    private String state;
    private String city;
    private String phone;
    private String email;


    public static final Parcelable.Creator<PersonalInfo> CREATOR = new Creator<PersonalInfo>() {
        @Override
        public PersonalInfo createFromParcel(Parcel parcel) {
            return new PersonalInfo(parcel);
        }

        @Override
        public PersonalInfo[] newArray(int size) {
            return new PersonalInfo[0];
        }
    };

    public PersonalInfo() {
    }

    public PersonalInfo(Parcel parcel) {
        name = parcel.readString();
        address = parcel.readString();
        state = parcel.readString();
        city = parcel.readString();
        phone = parcel.readString();
        email = parcel.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(state);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(email);
    }

    @Override
    public String toString() {
        return "personalInfo : {" + this.name + "|" + this.address + "|" + this.state + "|" + this.city + "|" + this.phone + "|" + this.email + "}";
    }
}
