package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by igMoreira on 05/11/17.
 */

public class Education implements Parcelable {
    private String institution;

    public Education() {}

    public Education(Parcel in) {
        institution = in.readString();
    }

    public static final Creator<Education> CREATOR = new Creator<Education>() {
        @Override
        public Education createFromParcel(Parcel in) {
            return new Education(in);
        }

        @Override
        public Education[] newArray(int size) {
            return new Education[size];
        }
    };

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }


    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(institution);
    }

    @Override
    public String toString() {
        return "education {"+institution+"}";
    }
}
