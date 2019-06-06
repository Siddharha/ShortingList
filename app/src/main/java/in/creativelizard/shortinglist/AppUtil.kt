package `in`.creativelizard.shortinglist

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class AppUtil {
    companion object{
        fun loadJSONFromAsset(context: Context, jsonFile:String): String {
            var json: String? = null
            try {
                val `is` = context.assets.open(jsonFile)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                json = String(buffer, Charset.defaultCharset())
            } catch (ex: IOException) {
                ex.printStackTrace()
                return "{}"
            }

            return json
        }

    }
}