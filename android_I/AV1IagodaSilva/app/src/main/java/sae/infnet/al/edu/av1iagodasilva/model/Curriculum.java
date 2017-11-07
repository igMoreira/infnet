package sae.infnet.al.edu.av1iagodasilva.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by igMoreira on 05/11/17.
 */

public class Curriculum implements Parcelable {

    private PersonalInfo personalInfo;
    private Education education;
    private Experience experience;
    private Course course;
    private Publication publication;

    public Curriculum() {
    }

    public Curriculum(Parcel in) {
        personalInfo = in.readParcelable(PersonalInfo.class.getClassLoader());
        education = in.readParcelable(Education.class.getClassLoader());
        experience = in.readParcelable(Experience.class.getClassLoader());
        course = in.readParcelable(Course.class.getClassLoader());
        publication = in.readParcelable(Publication.class.getClassLoader());
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
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
        dest.writeParcelable(education, flags);
        dest.writeParcelable(experience, flags);
        dest.writeParcelable(course, flags);
        dest.writeParcelable(publication, flags);
    }

    @Override
    public String toString() {
        return personalInfo.toString() + education.toString();
    }
}
