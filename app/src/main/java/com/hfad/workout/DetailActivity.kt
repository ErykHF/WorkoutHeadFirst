package com.hfad.workout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val EXTRA_WORKOUT_ID : String = "id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var workoutDetailFragment  = supportFragmentManager.findFragmentById(R.id.detail_frag) as WorkoutDetailFragment
        var workoutId = intent.extras!!.get(EXTRA_WORKOUT_ID) as Long
        workoutDetailFragment.setWorkout(workoutId)

    }
}
