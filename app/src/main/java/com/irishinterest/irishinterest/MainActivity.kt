package com.irishinterest.irishinterest

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.irishinterest.irishinterest.categories.CategoryListingFragment
import com.irishinterest.irishinterest.fragments.authors.AuthorsFragment
import com.irishinterest.irishinterest.fragments.authors.AuthorsProvider
import com.irishinterest.irishinterest.fragments.mainScreen.MainScreenFragment
import com.irishinterest.irishinterest.fragments.mainScreen.MainScreenProvider
import com.irishinterest.irishinterest.fragments.more.MoreScreenFragment
import com.irishinterest.irishinterest.fragments.search.SearchFragment
import com.irishinterest.irishinterest.helper.Notification
import com.irishinterest.irishinterest.helper.UserController
import com.irishinterest.irishinterest.network.NetworkAdapter
import com.irishinterest.irishinterest.network.api.irishInterest.observer.GuiObserver
import com.irishinterest.irishinterest.network.api.irishInterest.observer.Module
import java.util.*

class MainActivity : AppCompatActivity(), Notification {
    //Fragments
    private val categoryFragment = CategoryListingFragment()
    private val mainScreenFragment = MainScreenFragment()
    private val authorsFragment = AuthorsFragment()
    private val searchFragment = SearchFragment()

    //Layouts
    private var loadingScreenLayout: LinearLayout? = null
    private var coordinatorLayout: CoordinatorLayout? = null

    //Other
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private val guiObserver = GuiObserver()
    private var context: Context? = null
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //The application will use fragments. Will only use one activity for the foreseeable future.
        NetworkAdapter(guiObserver, this)
        context = this
        loadingScreenLayout = findViewById(R.id.loadingScreenMain)
        coordinatorLayout = findViewById(R.id.tabsss)
        if (!isNetworkAvailable) {
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_LONG).show()
            finish()
        } else {
            onStartApp()

            //For custom toolbar
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            
            createMainScreenFragment()
            createAuthorsScreenFragment()
//            createCategoryScreenFragment()

            drawerLayout = findViewById(R.id.drawerLayout)
            val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
            drawerLayout?.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()
            drawerLayout?.descendantFocusability = ViewGroup.FOCUS_BEFORE_DESCENDANTS
            supportActionBar?.let {
                it.setDisplayShowTitleEnabled(false)
                it.setHomeButtonEnabled(true)
                it.setDisplayHomeAsUpEnabled(true)
                it.setHomeAsUpIndicator(R.drawable.ic_menu)
                it.isHideOnContentScrollEnabled = false
            }
            viewPager = findViewById(R.id.viewpager)
            viewPager?.let {
                setupViewPager(it)
            }
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.setupWithViewPager(viewPager)
            tabs.bringToFront()
            tabs?.let {
                setupTabView(it)
            }
            supportActionBar!!.hide()
            setupSideMenu()
            tabLayout = tabs
            val user = UserController.getUser(applicationContext)
            user?.let {
                if (isAutoLoginEnabled) {
                    Toast.makeText(this, "Welcome back " + it.realName, Toast.LENGTH_LONG).show()
                } else {
                    UserController.logout(this)
                }
            }
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    (context as MainActivity).runOnUiThread {
                        val fadeOut = AlphaAnimation(1.0f, 0.0f)
                        loadingScreenLayout?.startAnimation(fadeOut)
                        fadeOut.duration = 4000
                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                (context as MainActivity).runOnUiThread {
                                    loadingScreenLayout?.setVisibility(View.GONE)
                                    tabLayout?.setVisibility(View.VISIBLE)
                                    (context as AppCompatActivity).supportActionBar!!.show()
                                    coordinatorLayout?.setVisibility(View.VISIBLE)
                                    viewPager?.setVisibility(View.VISIBLE)
                                    tabs.getTabAt(3)!!.select()
                                }
                            }
                        }, 4000)
                    }
                }
            }, 1000)
        }
    }

    private val isAutoLoginEnabled: Boolean
        get() {
            val sp = context!!.getSharedPreferences(
                    context!!.getString(R.string.enable_auto_login_setting), MODE_PRIVATE)
            return sp.getBoolean(context!!.getString(R.string.enable_auto_login_setting), true)
        }

    override fun onResume() {
        super.onResume()
        changeUserSection(UserController.isUserLoggedIn(applicationContext))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout!!.isDrawerVisible(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDataReceived(module: Module) {
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
        //            ((HomeActivity) context).runOnUiThread(() -> {
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

    private fun changeUserSection(userLoggedIn: Boolean) {
        val sideMenu = findViewById<LinearLayout>(R.id.sideMenu)
        if (userLoggedIn) {
            sideMenu.findViewById<View>(R.id.joinTextView).setOnClickListener { _ ->
                val intent = Intent(this, MyProfileActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            (sideMenu.findViewById<View>(R.id.joinTextView) as TextView).text = "My profile"
            sideMenu.findViewById<View>(R.id.signInTextView).setOnClickListener { _ ->
                UserController.logout(applicationContext)
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context!!.startActivity(intent)
            }
            (sideMenu.findViewById<View>(R.id.signInTextView) as TextView).text = "Logout"
        } else {
            sideMenu.findViewById<View>(R.id.joinTextView).setOnClickListener { _ ->
                val intent = Intent(context, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context!!.startActivity(intent)
            }
            (sideMenu.findViewById<View>(R.id.joinTextView) as TextView).text = "Join"
            sideMenu.findViewById<View>(R.id.signInTextView).setOnClickListener { _ ->
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context!!.startActivity(intent)
            }
            (sideMenu.findViewById<View>(R.id.signInTextView) as TextView).text = "Sign In"
        }
    }

    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    /**
     * This function initializes the side menu.
     * Here we define side menu button actions.
     */
    private fun setupSideMenu() {
        val sideMenu = findViewById<LinearLayout>(R.id.sideMenu)

        //Welcome
        sideMenu.findViewById<View>(R.id.welcomeTextView).setOnClickListener { _ ->
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Contact Us
        sideMenu.findViewById<View>(R.id.contactUsTextView).setOnClickListener { _ ->
            val intent = Intent(context, ContactUsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Join
        sideMenu.findViewById<View>(R.id.joinTextView).setOnClickListener { _ ->
            val intent = Intent(context, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Sign In
        sideMenu.findViewById<View>(R.id.signInTextView).setOnClickListener { _ ->
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Settings
        sideMenu.findViewById<View>(R.id.settingsTextView).setOnClickListener { _ ->
            val intent = Intent(context, SettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Privacy policy
        sideMenu.findViewById<View>(R.id.privacyPolicyTextView).setOnClickListener { _ ->
            val intent = Intent(context, PrivacyPolicyActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }

        //Terms & Conditions
        sideMenu.findViewById<View>(R.id.termsAndConditionsTextView).setOnClickListener { _ ->
            val intent = Intent(context, TermsAndConditionsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }
    }

    private fun setupTabView(tabLayout: TabLayout) {
        val names = ArrayList<String>()
        val icons = ArrayList<Int>()
        names.add("Search")
        icons.add(R.drawable.lookup_ic_white)
        names.add("Authors")
        icons.add(R.drawable.authors_icon)
        names.add("Categories")
        icons.add(R.drawable.categories_icon)
        names.add("Latest\r\nbooks")
        icons.add(R.drawable.books_icon)
        names.add("More")
        icons.add(R.drawable.more_icon)
        for (i in 0 until tabLayout.tabCount) {
            val tab = layoutInflater.inflate(R.layout.navigation_tab, null, false)
            val tabName = tab.findViewById<TextView>(R.id.navText)
            val tabIcon = tab.findViewById<ImageView>(R.id.navIcon)
            tabName.text = names[i]
            tabIcon.setImageResource(icons[i])
            tabLayout.getTabAt(i)?.customView = tab
        }
    }

    private fun onStartApp() {
        loadingScreenLayout!!.visibility = View.VISIBLE
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        loadingScreenLayout!!.startAnimation(fadeIn)
        fadeIn.duration = 2000
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(searchFragment, "Search")
        adapter.addFragment(authorsFragment, "Authors")
        adapter.addFragment(categoryFragment, "Categories")
        adapter.addFragment(mainScreenFragment, "Latest books")
        adapter.addFragment(MoreScreenFragment(), "More")
        viewPager.adapter = adapter
    }

    private fun createMainScreenFragment() {
        MainScreenProvider(guiObserver, mainScreenFragment)
    }

    private fun createAuthorsScreenFragment() {
        AuthorsProvider(guiObserver, authorsFragment)
    }

//    private fun createCategoryScreenFragment() {
        //Fragment Providers
//        CategoryProvider(guiObserver, categoryFragment)
//    }

    internal class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment?, title: String) {
            mFragmentList.add(fragment!!)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}