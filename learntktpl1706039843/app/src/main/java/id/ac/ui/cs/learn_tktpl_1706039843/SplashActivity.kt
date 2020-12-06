package id.ac.ui.cs.learn_tktpl_1706039843

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

/**
 * @author Bobby Akyong
 * */
class SplashActivity : Activity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}