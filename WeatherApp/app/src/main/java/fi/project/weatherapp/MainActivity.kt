package fi.project.weatherapp

import android.content.Intent
import android.os.Build
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
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var input: EditText? = null
    private var btn: Button? = null
    private var output: TextView? = null

    @RequiresApi(Build.VERSION_CODES.O)
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

                thread() {

                    var city: String = input?.text.toString()
                    var key: String = "6b939df2cb378451fd7012e6b3250865"
                    var url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$key"
                    
                    val apiResponse = URL(url).readText()                                   //.readText(Charsets.UTF_8)
                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")
                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")
                    val pressure = main.getString("pressure")
                    val humidity = main.getString("humidity")

                    val date = LocalDateTime.now()
                    val currentdate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))
                    val time = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

                    runOnUiThread() {
                        output?.text = "$currentdate, $time\n \nTemperature: $temp°  $desc  \nPressure: $pressure hPa  \nHumidity: $humidity % "

                    }
                    Thread.sleep(1000)
                }

                startService(Intent(this, MyBackgroundService::class.java))
                Log.d("MyBackgroundService", "Service started: $output")
            }

        })

    }
}



