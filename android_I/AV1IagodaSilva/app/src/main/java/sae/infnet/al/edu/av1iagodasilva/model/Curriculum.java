package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igMoreira on 05/11/17.
 */

public class Curriculum implements Parcelable{

    private PersonalInfo personalInfo;
    private List<Education> educationList = new ArrayList<Education>();


    public Curriculum() {
    }

    public Curriculum(Parcel in) {
        personalInfo = in.readParcelable(PersonalInfo.class.getClassLoader());
        educationList = in.readArrayList(Education.class.getClassLoader());
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Education> getEducationList() {
        return educationList;
    }


    public static final Creator<Curriculum> CREATOR = new Creator<Curriculum>() {
        @Override
        public Curriculum createFromParcel(Parcel in) {
            return new Curriculum(in);
        }

        @Override
        public Curriculum[] newArray(int size) {
            return new Curriculum[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(personalInfo, flags);
        dest.writeTypedList(educationList);
    }

    @Override
    public String toString() {
        return personalInfo.toString();
    }
}
