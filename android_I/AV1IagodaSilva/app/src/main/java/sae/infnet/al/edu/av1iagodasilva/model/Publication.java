package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by igMoreira on 07/11/17.
 */

public class Publication implements Parcelable{
    private String description;

    public Publication() {
    }

    protected Publication(Parcel in) {
        description = in.readString();
    }

    public static final Creator<Publication> CREATOR = new Creator<Publication>() {
        @Override
        public Publication createFromParcel(Parcel in) {
            return new Publication(in);
        }

        @Override
        public Publication[] newArray(int size) {
            return new Publication[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
    }
}
