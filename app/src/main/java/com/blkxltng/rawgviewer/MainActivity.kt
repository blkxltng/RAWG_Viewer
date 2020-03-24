package com.blkxltng.rawgviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blkxltng.rawgviewer.ui.main.MainFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        // Plant a tree so Timber logs will show
        Timber.plant(Timber.DebugTree())
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
