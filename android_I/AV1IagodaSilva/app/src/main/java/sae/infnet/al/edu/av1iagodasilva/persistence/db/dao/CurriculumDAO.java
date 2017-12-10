package sae.infnet.al.edu.av1iagodasilva.persistence.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

import sae.infnet.al.edu.av1iagodasilva.persistence.db.DbGateway;
import sae.infnet.al.edu.av1iagodasilva.model.Course;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.model.Education;
import sae.infnet.al.edu.av1iagodasilva.model.Experience;
import sae.infnet.al.edu.av1iagodasilva.model.PersonalInfo;
import sae.infnet.al.edu.av1iagodasilva.model.Publication;

/**
 * Created by igMoreira on 09/12/17.
 */

public class CurriculumDAO {

    private final String TABLE_CURRICULUM = "Curriculum";
    private DbGateway gtw;

    private final String UPDATE_QUERY = "UPDATE Curriculum SET name=? , address=? , state=? , city=? , phone=? , email=? , edu_institution=? , exp_position=? , exp_company=? , course_name=? , course_desc=? , pub_desc=? WHERE _id=? ;";

    private final String INSERT_QUERY = "INSERT INTO Curriculum ( _id, name, address, state, city, phone, email, edu_institution, exp_position, exp_company, course_name, course_desc, pub_desc )" +
            "SELECT ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? WHERE changes() = 0;";


    public CurriculumDAO(Context ctx) {
        gtw = DbGateway.getInstance(ctx);
    }

    public Curriculum update(Curriculum cv){
        SQLiteStatement updateStmt = gtw.getDatabase().compileStatement(UPDATE_QUERY);

        PersonalInfo info = cv.getPersonalInfo();
        Education edu = cv.getEducation();
        Experience exp = cv.getExperience();
        Course course = cv.getCourse();
        Publication pub = cv.getPublication();


        updateStmt.bindString(1, info.getName());
        updateStmt.bindString(2, info.getAddress());
        updateStmt.bindString(3, info.getState());
        updateStmt.bindString(4, info.getCity());
        updateStmt.bindString(5, info.getPhone());
        updateStmt.bindString(6, info.getEmail());

        updateStmt.bindString(7, edu.getInstitution());

        updateStmt.bindString(8, exp.getPosition());
        updateStmt.bindString(9, exp.getCompany());

        updateStmt.bindString(10, course.getName());
        updateStmt.bindString(11, course.getDescription());

        updateStmt.bindString(12, pub.getDescription());

        updateStmt.bindString(13, cv.getId());

        updateStmt.execute();

        return cv;
    }

    /**
     * Inserts if update fails.
     * @param cv
     * @return
     */
    public Curriculum insert(Curriculum cv){
        SQLiteStatement insertStmt = gtw.getDatabase().compileStatement(INSERT_QUERY);

        PersonalInfo info = cv.getPersonalInfo();
        Education edu = cv.getEducation();
        Experience exp = cv.getExperience();
        Course course = cv.getCourse();
        Publication pub = cv.getPublication();

        insertStmt.bindString(1, cv.getId());
        insertStmt.bindString(2, info.getName());
        insertStmt.bindString(3, info.getAddress());
        insertStmt.bindString(4, info.getState());
        insertStmt.bindString(5, info.getCity());
        insertStmt.bindString(6, info.getPhone());
        insertStmt.bindString(7, info.getEmail());

        insertStmt.bindString(8, edu.getInstitution());

        insertStmt.bindString(9, exp.getPosition());
        insertStmt.bindString(10, exp.getCompany());

        insertStmt.bindString(11, course.getName());
        insertStmt.bindString(12, course.getDescription());

        insertStmt.bindString(13, pub.getDescription());

        insertStmt.execute();

        return cv;
    }

    public Curriculum upsert(Curriculum cv){
        update(cv);
        insert(cv);
        return cv;
    }
}
