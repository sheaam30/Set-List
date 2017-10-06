package setlist.shea.setlist

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import setlist.shea.domain.di.RoomModule
import setlist.shea.setlist.di.ApplicationModule
import setlist.shea.setlist.di.DaggerApplicationComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Adam on 6/4/2017.
 */
class SetListApp : DaggerApplication() {

    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext


        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            //TODO
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .room(RoomModule())
                .applicationModule(ApplicationModule())
                .build()
    }
}