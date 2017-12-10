package sae.infnet.al.edu.av1iagodasilva;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import sae.infnet.al.edu.av1iagodasilva.activities.courses.CourseShowActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.education.EducationShowActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.experience.ExperienceShowActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.personalInfo.PersonalInfoRegisterActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.personalInfo.PersonalInfoShowActivity;
import sae.infnet.al.edu.av1iagodasilva.activities.publications.PublicationShowActivity;
import sae.infnet.al.edu.av1iagodasilva.model.Curriculum;
import sae.infnet.al.edu.av1iagodasilva.persistence.cache.CacheRepository;
import sae.infnet.al.edu.av1iagodasilva.persistence.db.dao.CurriculumDAO;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CURRICULUM_HEADER = "Curriculum_Header";

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int CV_REQUEST = 1;
    public static final String CV_RESPONSE = "Curriculum_Header_Response";

//    private static Curriculum CV = new Curriculum();

    private CurriculumDAO dao;
    private CacheRepository cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Curriculum cv = cache.restore();
                Log.d(TAG, "Restored from cache | UID: " + cv.getId());
                Intent intent = new Intent(MainActivity.this, PersonalInfoRegisterActivity.class);
                intent.putExtra(CURRICULUM_HEADER, cv);

                Log.d(TAG, "Starting curriculum registration process... | UID: " + cv.getId());
                startActivityForResult(intent, CV_REQUEST);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dao = new CurriculumDAO(this);
        cache = new CacheRepository(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Curriculum cv = cache.restore();
        Log.d(TAG, "Restored from cache | UID: " + cv.getId());

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_personalinfo) {
            // Handle the camera action
            Intent intent = new Intent(this, PersonalInfoShowActivity.class);
            intent.putExtra(CURRICULUM_HEADER, cv);
            startActivity(intent);
        }
        else if (id == R.id.nav_education){
            Intent intent = new Intent(this, EducationShowActivity.class);
            intent.putExtra(CURRICULUM_HEADER, cv);
            startActivity(intent);
        }
        else if ( id == R.id.nav_experience) {
            Intent intent = new Intent(this, ExperienceShowActivity.class);
            intent.putExtra(CURRICULUM_HEADER, cv);
            startActivity(intent);
        }
        else if ( id == R.id.nav_courses) {
            Intent intent = new Intent(this, CourseShowActivity.class);
            intent.putExtra(CURRICULUM_HEADER, cv);
            startActivity(intent);
        }
        else if ( id == R.id.nav_publications) {
            Intent intent = new Intent(this, PublicationShowActivity.class);
            intent.putExtra(CURRICULUM_HEADER, cv);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CV_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                Curriculum cv = data.getParcelableExtra(MainActivity.CV_RESPONSE);
                Log.d(TAG, "Curriculum registration complete! ");
                Log.d(TAG, cv.toString());
                dao.upsert(cv);
                Log.d(TAG, "Saving in DB | UID: " + cv.getId());
                cache.save(cv);
                Log.d(TAG, "Saved in Cache | UID: " + cv.getId());
            }
        }
    }

}
