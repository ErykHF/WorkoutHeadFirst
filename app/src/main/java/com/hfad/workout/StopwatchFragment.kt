package com.hfad.workout


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class StopwatchFragment : Fragment(), View.OnClickListener {

    private var seconds: Int = 0
    private var isRunning: Boolean = false
    private var wasRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        savedInstanceState?.let {
            seconds = it.getInt("seconds")
            isRunning = it.getBoolean("isRunning")
            wasRunning = it.getBoolean("wasRunning")
            if (wasRunning) {
                isRunning = true
            }

        }


        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        runTimer(layout)
        val startButton: Button = layout.findViewById(R.id.start_button)
        startButton.setOnClickListener(this)
        val stopButton: Button = layout.findViewById(R.id.stop_button)
        stopButton.setOnClickListener(this)
        val resetButton: Button = layout.findViewById(R.id.reset_button)
        resetButton.setOnClickListener(this)
        return layout
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("isRunning", isRunning)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onStop() {
        super.onStop()
        wasRunning = isRunning
        isRunning = false
    }

    override fun onStart() {
        super.onStart()
        if (wasRunning) {
            isRunning = true
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> startTimer(v)
            R.id.stop_button -> stopTimer(v)
            R.id.reset_button -> resetTimer(v)
        }
    }


    fun startTimer(view: View) {
        isRunning = true

    }

    fun stopTimer(view: View) {
        isRunning = false

    }

    fun resetTimer(view: View) {

        isRunning = false
        seconds = 0

    }


    private fun runTimer(view: View) {
        val handler = Handler()
        val timeView: TextView = view.findViewById(R.id.time_view)

        handler.post(object : Runnable {
            override fun run() {
                val hours: Int = seconds / 3600
                val minutes: Int = (seconds % 3600) / 60
                val secs: Int = seconds % 60

                val time: String = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (isRunning) {
                    seconds++

                }
                handler.postDelayed(this, 1000)
            }
        })

    }
}