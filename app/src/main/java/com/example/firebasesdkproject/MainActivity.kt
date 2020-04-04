package com.example.firebasesdkproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasesdkproject.fragments.IssueDetailFragment
import com.example.firebasesdkproject.fragments.IssueDetailFragment.Companion.bundleData
import com.example.firebasesdkproject.fragments.IssueListFragment
import com.example.firebasesdkproject.model.IssueModelItem
import io.realm.Realm


class MainActivity : AppCompatActivity() {

    private var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        realm = Realm.getDefaultInstance()

        if (savedInstanceState == null) {
            val fragment = IssueListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, "issuelist").commit()
        }

    }

    fun detailView(issue: IssueModelItem?) {
        val issueDetailFragment = bundleData(issue?.id, issue?.comments_url)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("issueDetail")
            .replace(R.id.fragment_container, issueDetailFragment, null).commit()
    }
}
