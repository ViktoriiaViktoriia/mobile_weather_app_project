package fi.project.weatherapp


import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {
    //binded service is not used
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("MyBackgroundService", "Service started")

        while (false) {
            //Log.d("MyBackgroundService", "Service stopped")
        }




        // If service is killed by android, it will try to start it again when possible
        return START_STICKY
    }
}
