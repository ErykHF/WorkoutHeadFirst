package com.hfad.workout


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_workout_detail.*

/**
 * A simple [Fragment] subclass.
 */
class WorkoutDetailFragment : Fragment() {

    private var workoutId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId")
        }else {

            val ft = childFragmentManager.beginTransaction()
            val stopwatchFragment = StopwatchFragment()
            ft.replace(R.id.stopwatch_container, stopwatchFragment)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val workout = Workout.workouts[workoutId.toInt()]
            textTitle.text = workout.name
            textDescription.text = workout.description
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("workoutId", workoutId)
    }

    fun setWorkout(id: Long) {
        this.workoutId = id
    }


}
