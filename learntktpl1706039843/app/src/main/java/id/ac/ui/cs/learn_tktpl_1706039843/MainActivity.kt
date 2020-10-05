package id.ac.ui.cs.learn_tktpl_1706039843

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var handler: Handler? = null
    var hour: TextView? = null
    var minute: TextView? = null
    var seconds: TextView? = null
    private var startButton: ImageButton? = null

    internal var millisecondTime: Long = 0
    internal var startTime: Long = 0
    internal var timeBuff: Long = 0
    internal var updateTime = 0L

    internal var secondsInt: Int = 0
    internal var minutesInt: Int = 0
    internal var milliSecondsInt: Int = 0

    private var flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.startButton)
        bindView()
    }

    override fun onBackPressed() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Do you want to exit? ")
        builder.setPositiveButton("Exit") { dialog, id ->
            this.finish()
            super@MainActivity.onBackPressed()
        }
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
        builder.show()
    }

    private fun bindView() {

        hour = findViewById(R.id.hour)
        minute = findViewById(R.id.minute)
        seconds = findViewById(R.id.seconds)

        startButton?.setOnClickListener {
            if (flag){
                handler?.removeCallbacks(runnable)
                startButton?.setImageResource(R.drawable.ic_play)
                flag=false
            }else{
                startButton?.setImageResource(R.drawable.pause)
                startTime = SystemClock.uptimeMillis()
                handler?.postDelayed(runnable, 0)
                flag=true
            }

        }

        handler = Handler(Looper.getMainLooper())

    }

    private var runnable: Runnable = object : Runnable {

        override fun run() {

            millisecondTime = SystemClock.uptimeMillis() - startTime
            updateTime = timeBuff + millisecondTime
            secondsInt = (updateTime / 1000).toInt()
            minutesInt = secondsInt / 60
            secondsInt %= 60
            milliSecondsInt = (updateTime % 1000).toInt()

            if (minutesInt.toString().length < 2) {
                minute?.text = "0$minutesInt"
            } else {
                minute?.text = minutesInt.toString()
            }
            if (secondsInt.toString().length < 2) {
                seconds?.text = "0$secondsInt"
            } else {
                seconds?.text = secondsInt.toString()
            }

            handler?.postDelayed(this, 0)
        }

    }


}