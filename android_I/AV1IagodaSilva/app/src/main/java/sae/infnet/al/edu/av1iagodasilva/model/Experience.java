package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by igMoreira on 06/11/17.
 */

public class Experience implements Parcelable{
    String position;
    String company;

    public Experience() {
    }

    protected Experience(Parcel in) {
        position = in.readString();
        company = in.readString();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(position);
        dest.writeString(company);
    }
}
