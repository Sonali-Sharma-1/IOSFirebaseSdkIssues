package com.example.firebasesdkproject.RealmDb

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class RealmApplication : Application {

    constructor() {}

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .schemaVersion(1)
            .name("firebase_db_issues.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(configuration)
    }

}