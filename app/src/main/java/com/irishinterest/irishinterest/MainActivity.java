package com.irishinterest.irishinterest;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.irishinterest.irishinterest.fragments.authors.AuthorsFragment;
import com.irishinterest.irishinterest.fragments.authors.AuthorsProvider;
import com.irishinterest.irishinterest.fragments.categories.CategoryFragment;
import com.irishinterest.irishinterest.fragments.categories.CategoryProvider;
import com.irishinterest.irishinterest.fragments.detailedBooks.BookDetailedProvider;
import com.irishinterest.irishinterest.fragments.mainScreen.MainScreenFragment;
import com.irishinterest.irishinterest.fragments.mainScreen.MainScreenProvider;
import com.irishinterest.irishinterest.fragments.more.MoreScreenFragment;
import com.irishinterest.irishinterest.fragments.search.SearchFragment;
import com.irishinterest.irishinterest.helper.Notification;
import com.irishinterest.irishinterest.helper.UserController;
import com.irishinterest.irishinterest.model.user.UserValues;
import com.irishinterest.irishinterest.network.NetworkAdapter;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver;
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity implements Notification {

    //Fragments
    private CategoryFragment categoryFragment;
    private MainScreenFragment mainScreenFragment;
    private MoreScreenFragment moreScreenFragment;
    private AuthorsFragment authorsFragment;
    private SearchFragment searchFragment;

    //Fragment Providers
    private CategoryProvider categoryProvider;
    private BookDetailedProvider bookDetailedProvider;
    private MainScreenProvider mainScreenProvider;
    private AuthorsProvider authorsProvider;

    //Layouts
    private LinearLayout loadingScreenLayout;
    private LinearLayout mainScreenLayout;
    private CoordinatorLayout coordinatorLayout;

    //Other
    private AlphaAnimation fadeIn;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NetworkAdapter networkAdapter;
    private GuiObserver guiObserver;
    private Context context;

    private boolean categoriesDone = false;

   private ActionBarDrawerToggle actionBarDrawerToggle;
   private DrawerLayout drawerLayout;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //The application will use fragments. Will only use one activity for the foreseeable future.
        guiObserver = new GuiObserver();
        networkAdapter = new NetworkAdapter(guiObserver, this);
        context = this;
        loadingScreenLayout = findViewById(R.id.loadingScreenMain);
        coordinatorLayout = findViewById(R.id.tabsss);

        if(!isNetworkAvailable()){
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show();
            finish();
        }else {
 
            onStartApp();

            //For custom toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            createSearchScreenFragment();
            createMainScreenFragment();
            createAuthorsScreenFragment();
            createCategoryScreenFragment();

            drawerLayout = findViewById(R.id.drawerLayout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
            drawerLayout.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);

            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            getSupportActionBar().setHideOnContentScrollEnabled(false);
            viewPager = findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.bringToFront();
            setupTabView(tabLayout);
            getSupportActionBar().hide();
            setupSideMenu();
            UserValues user = UserController.getUser(getApplicationContext());
            if (user != null) {
                if(isAutoLoginEnabled()) {
                    Toast.makeText(this, "Welcome back " + user.getRealName(), Toast.LENGTH_LONG).show();
                } else {
                    UserController.logout(this);
                }
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                            loadingScreenLayout.startAnimation(fadeOut);
                            fadeOut.setDuration(4000);
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ((MainActivity) context).runOnUiThread(() -> {
                                        loadingScreenLayout.setVisibility(GONE);
                                        tabLayout.setVisibility(View.VISIBLE);
                                        ((AppCompatActivity) context).getSupportActionBar().show();
                                        coordinatorLayout.setVisibility(View.VISIBLE);
                                        viewPager.setVisibility(View.VISIBLE);
                                        tabLayout.getTabAt(3).select();
                                    });
                                }
                            }, 4000);
                        }
                    });

                }
            }, 1000);



        }
    }

    private boolean isAutoLoginEnabled(){
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.enable_auto_login_setting), Context.MODE_PRIVATE);
        return sp.getBoolean(context.getString(R.string.enable_auto_login_setting),true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeUserSection(UserController.isUserLoggedIn(getApplicationContext()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
           case android.R.id.home:
               if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                   drawerLayout.closeDrawer(GravityCompat.START);
               } else {
                   drawerLayout.openDrawer(GravityCompat.START);
               }
               break;
       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataReceived(Module module) {
        //if(module == Module.CATEGORIES){
        //    categoriesDone = true;
        //}

        //if(categoriesDone) {
        //    AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        //    loadingScreenLayout.startAnimation(fadeOut);
        //    fadeOut.setDuration(3000);

        //    new Timer().schedule(new TimerTask() {
        //        @Override
        //        public void run() {
        //            ((MainActivity) context).runOnUiThread(() -> {
        //                loadingScreenLayout.setVisibility(GONE);
        //                tabLayout.setVisibility(View.VISIBLE);
        //                ((AppCompatActivity) context).getSupportActionBar().show();
        //                coordinatorLayout.setVisibility(View.VISIBLE);
        //                viewPager.setVisibility(View.VISIBLE);
        //                tabLayout.getTabAt(2).select();
        //            });
        //        }
        //    }, 3000);
        //}
    }

    private void changeUserSection(boolean userLoggedIn){
        LinearLayout sideMenu = findViewById(R.id.sideMenu);
        if(userLoggedIn){
            sideMenu.findViewById(R.id.joinTextView).setOnClickListener(v -> {
                Intent intent = new Intent(context, MyProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            ((TextView)sideMenu.findViewById(R.id.joinTextView)).setText("My profile");

            sideMenu.findViewById(R.id.signInTextView).setOnClickListener(v -> {
                UserController.logout(getApplicationContext());
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            ((TextView)sideMenu.findViewById(R.id.signInTextView)).setText("Logout");
        } else {
            sideMenu.findViewById(R.id.joinTextView).setOnClickListener(v -> {
                Intent intent = new Intent(context, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);            });
            ((TextView)sideMenu.findViewById(R.id.joinTextView)).setText("Join");

            sideMenu.findViewById(R.id.signInTextView).setOnClickListener(v -> {
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            ((TextView)sideMenu.findViewById(R.id.signInTextView)).setText("Sign In");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * This function initializes the side menu.
     * Here we define side menu button actions.
     */
    private void setupSideMenu(){
        LinearLayout sideMenu = findViewById(R.id.sideMenu);

        //Welcome
        sideMenu.findViewById(R.id.welcomeTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Contact Us
        sideMenu.findViewById(R.id.contactUsTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, ContactUsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Join
        sideMenu.findViewById(R.id.joinTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Sign In
        sideMenu.findViewById(R.id.signInTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Settings
        sideMenu.findViewById(R.id.settingsTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Privacy policy
        sideMenu.findViewById(R.id.privacyPolicyTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, PrivacyPolicyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //Terms & Conditions
        sideMenu.findViewById(R.id.termsAndConditionsTextView).setOnClickListener(v -> {
            Intent intent = new Intent(context, TermsAndConditionsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    private void setupTabView(TabLayout tabLayout){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> icons = new ArrayList<>();
        names.add("Search");
        icons.add(R.drawable.lookup_ic_white);
        names.add("Authors");
        icons.add(R.drawable.authors_icon);
        names.add("Categories");
        icons.add(R.drawable.categories_icon);
        names.add("Latest\r\nbooks");
        icons.add(R.drawable.books_icon);
        names.add("More");
        icons.add(R.drawable.more_icon);

        for(int i = 0; i < tabLayout.getTabCount(); i++){
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.navigation_tab, null);
            TextView tabName = tab.findViewById(R.id.navText);
            ImageView tabIcon = tab.findViewById(R.id.navIcon);
            tabName.setText(names.get(i));
            tabIcon.setImageResource(icons.get(i));
            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }

    private void onStartApp(){
        loadingScreenLayout.setVisibility(View.VISIBLE);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        loadingScreenLayout.startAnimation(fadeIn);
        fadeIn.setDuration(2000);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(searchFragment, "Search");
        adapter.addFragment(authorsFragment, "Authors");
        adapter.addFragment(categoryFragment, "Categories");
        adapter.addFragment(mainScreenFragment, "Latest books");
        adapter.addFragment(new MoreScreenFragment(), "More");
        viewPager.setAdapter(adapter);
    }

    private void createMainScreenFragment(){
        mainScreenFragment = new MainScreenFragment();
        mainScreenProvider = new MainScreenProvider(guiObserver, mainScreenFragment);
    }

    private void createAuthorsScreenFragment(){
        authorsFragment = new AuthorsFragment();
        authorsProvider = new AuthorsProvider(guiObserver, authorsFragment);
    }

    private void createCategoryScreenFragment(){
        categoryFragment = new CategoryFragment();
        categoryProvider = new CategoryProvider(guiObserver, categoryFragment);
    }

    private void createSearchScreenFragment(){
        searchFragment = new SearchFragment();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        
    }
}
