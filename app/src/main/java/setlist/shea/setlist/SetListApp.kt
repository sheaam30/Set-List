package setlist.shea.setlist

import android.app.Application
import android.content.Context

/**
 * Created by Adam on 6/4/2017.
 */
class SetListApp : Application() {
    companion object {
        @JvmField
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        SetListApp.context = applicationContext
    }
}