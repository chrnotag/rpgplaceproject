package com.example.meow

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.meow.fragments.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    var MY_ACCOUNT = 1
    var FRIENDS = 2
    var HOME = 3
    var NOTIFICATIONS = 4
    var SETTINGS = 5
    var press = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        nav.show(HOME)

        nav.setBackgroundColor(Color.TRANSPARENT)

        abrirHome()

        addOnMeowNavigattionBarIcons()
        addOnNotificationInform()

        chamarFragments()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Pressione novamente para sair", Toast.LENGTH_SHORT).show()
    }

    fun abrirHome(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = HomeFragment()
        fragmentTransaction.replace(R.id.parentfragments, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addOnMeowNavigattionBarIcons(){
        nav.add(MeowBottomNavigation.Model(MY_ACCOUNT,R.drawable.ic_baseline_account_circle_24))
        nav.add(MeowBottomNavigation.Model(FRIENDS,R.drawable.ic_baseline_people_24))
        nav.add(MeowBottomNavigation.Model(HOME,R.drawable.ic_baseline_home_24))
        nav.add(MeowBottomNavigation.Model(NOTIFICATIONS,R.drawable.ic_baseline_notifications_24))
        nav.add(MeowBottomNavigation.Model(SETTINGS,R.drawable.ic_baseline_settings_24))
    }

    fun addOnNotificationInform(){
        nav.setCount(NOTIFICATIONS,"99")
    }

    fun chamarFragments(){
        nav.setOnClickMenuListener {

            when(it.id){
                HOME -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = HomeFragment()
                    fragmentTransaction.replace(R.id.parentfragments, fragment)
                    fragmentTransaction.commit()
                }

                MY_ACCOUNT -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = MyAccountFragment()

                    fragmentTransaction.replace(R.id.parentfragments, fragment)
                    fragmentTransaction.commit()
                }

                FRIENDS -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = FriendsFragment()

                    fragmentTransaction.replace(R.id.parentfragments,fragment)
                    fragmentTransaction.commit()

                }

                NOTIFICATIONS -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = NotificationsFragment()

                    fragmentTransaction.replace(R.id.parentfragments,fragment)
                    fragmentTransaction.commit()
                }

                SETTINGS -> {
                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val fragment = SettingsFragment()

                    fragmentTransaction.replace(R.id.parentfragments,fragment)
                    fragmentTransaction.commit()
                }

            }
        }
    }

    fun voltarLogin(){
        val intent = Intent(this, login::class.java)
        startActivity(intent)
        finish()
    }

}