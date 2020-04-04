package com.example.firebasesdkproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.firebasesdkproject.fragments.IssueListFragment
import io.realm.Realm
import java.util.*


class MainActivity : AppCompatActivity() {

    private var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        realm = Realm.getDefaultInstance()

        if (savedInstanceState == null) {
            val fragment =  IssueListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "issuelist").commit()
        }

    }
}
