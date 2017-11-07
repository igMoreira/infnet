package sae.infnet.al.edu.av1iagodasilva.activities.courses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sae.infnet.al.edu.av1iagodasilva.MainActivity;
import sae.infnet.al.edu.av1iagodasilva.R;
import sae.infnet.al.edu.av1iagodasilva.model.Course;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;

public class CourseShowActivity extends AppCompatActivity {

    TextView nameField;
    TextView descriptionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_show);

        nameField = (TextView) findViewById(R.id.coursename_show_text);
        descriptionField = (TextView) findViewById(R.id.coursedescription_show_text);

        Intent intent = getIntent();
        Curriculum cv = intent.getParcelableExtra(MainActivity.CURRICULUM_HEADER);

        Course course = cv.getCourse();

        if (course != null) {
            nameField.setText(course.getName());
            descriptionField.setText(course.getDescription());
        }
    }
}
