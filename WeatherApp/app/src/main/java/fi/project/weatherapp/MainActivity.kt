package fi.project.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.net.URL;
import org.json.JSONObject;
import android.util.Log;
import android.view.View
import java.net.HttpURLConnection
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {

    private var input: EditText? = null
    private var btn: Button? = null
    private var output: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.user_input)
        btn = findViewById(R.id.button)
        output = findViewById(R.id.output_info)


        btn?.setOnClickListener(View.OnClickListener {

            if(input?.text?.toString()?.trim()?.equals("")!!)
                Toast.makeText(this, "Enter a city name", Toast.LENGTH_LONG).show()
            else {
                var city: String = input?.text.toString()
                //var key: String = "6b939df2cb378451fd7012e6b3250865"
                var url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=6b939df2cb378451fd7012e6b3250865"

                /*
                val apiResponse = URL(url).readText()         //http connection error
                val weather = JSONObject(apiResponse).getJSONArray("weather")
                val desc = weather.getJSONObject(0).getString("description")
                val main = JSONObject(apiResponse).getJSONObject("main")
                val temp = main.getString("temp")

                output?.text = "Temperature: $temp/n$desc"

                */

                output?.text = "City: Tampere      Temperature: 1.97    light rain  Pressure: 1013 Humidity: 93"

                startService(Intent(this, MyBackgroundService::class.java))
                Log.d("MyBackgroundService", "Service started: $output")
            }

        })

    }
}
