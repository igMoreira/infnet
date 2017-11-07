package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by igMoreira on 07/11/17.
 */

public class Course implements Parcelable {
    private String name;
    private String description;

    public Course() {
    }

    protected Course(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        dest.writeString(name);
        dest.writeString(description);
    }
}
