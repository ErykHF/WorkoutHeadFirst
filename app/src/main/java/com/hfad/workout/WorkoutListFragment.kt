package com.hfad.workout


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment


/**
 * A simple [Fragment] subclass.
 */
class WorkoutListFragment : ListFragment() {

    internal interface WorkoutListListener {
        fun itemClicked(id: Long)
    }

    private var listener: WorkoutListListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val names = arrayOfNulls<String>(Workout.workouts.size)

        names.let {
            for (item in names.indices) {
                names[item] = Workout.workouts[item].name
            }
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            inflater.context, android.R.layout.simple_list_item_1, names
        )

        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.listener = activity as WorkoutListListener

    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener!!.itemClicked(id)
    }

}

