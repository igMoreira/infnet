package sae.infnet.al.edu.av1iagodasilva.persistence.cache;

import android.content.Context;
import android.content.SharedPreferences;

import sae.infnet.al.edu.av1iagodasilva.model.Course;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Education;
import sae.infnet.al.edu.av1iagodasilva.model.Experience;
import sae.infnet.al.edu.av1iagodasilva.model.PersonalInfo;
import sae.infnet.al.edu.av1iagodasilva.model.Publication;

import static android.content.SharedPreferences.Editor;


/**
 * Created by igMoreira on 10/12/17.
 */

public class CacheRepository {

    private final static String CACHE_PREFS = "sae.infnet.al.edu.av2iagodasilva.curriculum";
    private Context ctx;

    public CacheRepository(Context ctx) {
        this.ctx = ctx;
    }

    public Curriculum save(Curriculum cv) {
        Editor editor = ctx.getSharedPreferences(CACHE_PREFS, ctx.MODE_PRIVATE).edit();
        editor.putString("_id", cv.getId());

        PersonalInfo info = cv.getPersonalInfo();
        editor.putString("name", info.getName());
        editor.putString("address", info.getAddress());
        editor.putString("state", info.getState());
        editor.putString("city", info.getCity());
        editor.putString("phone", info.getPhone());
        editor.putString("email", info.getEmail());

        Education edu = cv.getEducation();
        editor.putString("institution", edu.getInstitution());

        Experience exp = cv.getExperience();
        editor.putString("position", exp.getPosition());
        editor.putString("company", exp.getCompany());

        Course c = cv.getCourse();
        editor.putString("courseName", c.getName());
        editor.putString("courseDescription", c.getDescription());

        Publication pub = cv.getPublication();
        editor.putString("publication", pub.getDescription());

        editor.apply();

        return cv;
    }

    public Curriculum restore() {

        SharedPreferences prefs = ctx.getSharedPreferences(CACHE_PREFS, ctx.MODE_PRIVATE);

        PersonalInfo info = new PersonalInfo();
        info.setName(prefs.getString("name", ""));
        info.setAddress(prefs.getString("address",""));
        info.setState(prefs.getString("state",""));
        info.setCity(prefs.getString("city",""));
        info.setPhone(prefs.getString("phone",""));
        info.setEmail(prefs.getString("email",""));

        Education edu = new Education();
        edu.setInstitution(prefs.getString("institution",""));

        Experience exp = new Experience();
        exp.setPosition(prefs.getString("position",""));
        exp.setCompany(prefs.getString("company",""));

        Course c = new Course();
        c.setName(prefs.getString("courseName",""));
        c.setDescription(prefs.getString("courseDescription",""));

        Publication pub = new Publication();
        pub.setDescription(prefs.getString("publication",""));

        Curriculum cv = new Curriculum();
        String _id = prefs.getString("_id",null);
        if ( _id != null)
            cv.setId(_id);
        cv.setPersonalInfo(info);
        cv.setEducation(edu);
        cv.setExperience(exp);
        cv.setCourse(c);
        cv.setPublication(pub);

        return cv;
    }
}
