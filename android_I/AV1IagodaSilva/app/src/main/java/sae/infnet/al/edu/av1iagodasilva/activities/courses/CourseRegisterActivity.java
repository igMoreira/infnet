package sae.infnet.al.edu.av1iagodasilva.activities.courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.activities.CurriculumStepActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.publications.PublicationRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Course;

public class CourseRegisterActivity extends CurriculumStepActivity {

    EditText courseNameField;
    EditText courseDescriptionField;

    public CourseRegisterActivity() {
        super(PublicationRegisterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_register);

        courseDescriptionField = (EditText) findViewById(R.id.coursedescription_register_editText);
        courseNameField = (EditText) findViewById(R.id.coursename_register_editText);
    }

    @Override
    protected void agregateMessage() {
        Course course = new Course();
        course.setName(courseNameField.getText().toString());
        course.setDescription(courseDescriptionField.getText().toString());

        this.cv.setCourse(course);
    }
}
