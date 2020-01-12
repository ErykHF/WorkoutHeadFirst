package com.hfad.workout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), WorkoutListFragment.WorkoutListListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun itemClicked(id: Long) {

        val fragmentContainer = findViewById<View>(R.id.fragment_container)


        if (fragmentContainer != null) {
            val details = WorkoutDetailFragment()
            val ft = supportFragmentManager.beginTransaction()
            details.setWorkout(id)
            ft.replace(R.id.fragment_container, details)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}


