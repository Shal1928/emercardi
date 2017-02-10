package ru.shal1928.emercardi.app.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.parts.ExtAppCompatActivity;
import ru.shal1928.emercardi.app.fragments.PlanetFragment;
import ru.shal1928.emercardi.app.helpers.IntentBuilder;
import ru.shal1928.emercardi.app.models.PersonalInfo;
import ru.shal1928.emercardi.app.models.parts.IPersonalInfo;
import ru.shal1928.emercardi.app.viewmodels.MainViewModel;

import java.util.Calendar;

public class MainActivity extends ExtAppCompatActivity implements View.OnClickListener {

    static final int PERSONAL_INFO_REQUEST = 0;

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private Animation fab_open, fab_close, rotate_forward;
    private Animation rotate_backward;
    private FloatingActionButton fab, fab1, fab2;
    private LinearLayout fab1_layout, fab2_layout;
    private Boolean isFabOpen = false;

    private Runnable runnable;

    private MainViewModel mainViewModel;

    public MainActivity() {
        super(R.menu.menu_main);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar(R.id.toolbar, true, true);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName("Константин");
        personalInfo.setLastName("Константинопольский");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1990, 29, 02);
        personalInfo.setDateOfBirth(calendar);
        personalInfo.setHeight(180);
        personalInfo.setWeight(93);
        this.mainViewModel = new MainViewModel();
        this.mainViewModel.setPersonalInfo(personalInfo);

        fab1_layout = (LinearLayout) findViewById(R.id.fab1_layout);
        fab2_layout = (LinearLayout) findViewById(R.id.fab2_layout);

        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);


        mPlanetTitles = getResources().getStringArray(R.array.drawer_elements_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
            R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(),
            R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);

//        final View v = fab;
        runnable = new Runnable() {
            @Override public void run() {
                fab.startAnimation(rotate_forward);

                fab1_layout.startAnimation(fab_open);
                fab2_layout.startAnimation(fab_open);
            }
        };
    }

    @Override public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:
//                Intent intent = new Intent(this, PersonalInfoActivity.class);
                IntentBuilder intentBuilder = new IntentBuilder(this, PersonalInfoActivity.class);

//                this.mainViewModel.withPersonalInfo(intent);
                //Переделать на билдер все таки
                startActivityForResult(intentBuilder.with(this.mainViewModel.getPersonalInfo()), PERSONAL_INFO_REQUEST);


                fab.clearAnimation();
                fab1_layout.clearAnimation();
                fab2_layout.clearAnimation();
                isFabOpen = false;
                break;
            case R.id.fab2:

                Log.d("Raj", "Fab 2");
                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1_layout.startAnimation(fab_close);
            fab2_layout.startAnimation(fab_close);

            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                fab.postDelayed(runnable, 51); // не работает нормально анимация
            }
            else {
                fab.startAnimation(rotate_forward);

                fab1_layout.startAnimation(fab_open);
                fab2_layout.startAnimation(fab_open);
            }



            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PERSONAL_INFO_REQUEST) {
            if (resultCode == RESULT_OK) {
                this.mainViewModel.setPersonalInfo(
                        (IPersonalInfo) IntentBuilder.getRealModel(data, PersonalInfo.class));
            }
        }

        Log.d("USER: ", this.mainViewModel.getPersonalInfo().getFirstName() + " " +  this.mainViewModel
                .getPersonalInfo().getLastName());
        Log.d("USER: ",  this.mainViewModel.getPersonalInfo().getDateOfBirth().toString());
        Log.d("USER: ", this.mainViewModel.getPersonalInfo().getHeight() + " " +
                this.mainViewModel.getPersonalInfo().getWeight());
    }
}
