package id.ac.ui.cs.learn_tktpl_1706039843

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var wifiListView: ListView
    private lateinit var wifiManager: WifiManager
    private lateinit var requestQueue: RequestQueue
    val deviceList: ArrayList<String> = ArrayList()

    private var receiverWifi: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION == action) {
                var sb = StringBuilder()
                val wifiList: List<ScanResult> = wifiManager.scanResults
                deviceList.clear()
                for (scanResult in wifiList) {
                    sb!!.append("\n").append(scanResult.SSID)
                    deviceList.add(scanResult.SSID.toString())
                }

                val arrayAdapter: ArrayAdapter<Any>? =
                    context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, deviceList.toArray()) }
                wifiListView.adapter  = arrayAdapter
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiListView = findViewById(R.id.wifiList)
        var buttonScan = findViewById<Button>(R.id.scanButton)
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        requestQueue = Volley.newRequestQueue(this)

        if (!wifiManager.isWifiEnabled) {
            Toast.makeText(applicationContext, "Turning WiFi on...", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        buttonScan.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1
                )
            } else {
                wifiManager.startScan()
                sendData()
            }
        }

    }

    override fun onPostResume() {
        super.onPostResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(receiverWifi, intentFilter)
        getWifi()
    }

    private fun sendData() {
        val obj = JSONObject()
        try {
            obj.put("wifi_list", deviceList)
        } catch (e: JSONException) {
            e.printStackTrace()
            Toast.makeText(this@MainActivity, "Failed to send data", Toast.LENGTH_SHORT).show()
        }

        val url = "https://3293d9068ffec84e6fe83fd24e830b4d.m.pipedream.net"

        val request =
            JsonObjectRequest(Request.Method.POST, url, obj, { _ ->
                try {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, { error -> error.printStackTrace() })
        requestQueue.add(request)
    }

    private fun getWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Location turned off", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    1
                )
            } else {
                Toast.makeText(this@MainActivity, "Location turned on", Toast.LENGTH_SHORT).show()
                wifiManager.startScan()
            }
        } else {
            Toast.makeText(this@MainActivity, "Scanning...", Toast.LENGTH_SHORT).show()
            wifiManager.startScan()
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiverWifi)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) === PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}

