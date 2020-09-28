package id.ac.ui.cs.learn_tktpl_1706039843

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val obj = Counter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plusButton.setOnClickListener {
            obj.incrementCount()
            updateCount(obj.getCount())
        }

        minusButton.setOnClickListener {
            if (obj.countChecker()) {
                obj.decrementCount()
                updateCount(obj.getCount())
            }
        }

        resetButton.setOnClickListener {
            obj.resetCount()
            updateCount(obj.getCount())
        }

    }

    private fun updateCount(counter: Int) {
        val textView = findViewById<TextView>(R.id.value)
        textView.text = counter.toString()
    }


}