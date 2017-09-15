package setlist.shea.setlist

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import setlist.shea.domain.di.RoomModule
import setlist.shea.setlist.di.ApplicationModule
import setlist.shea.setlist.di.DaggerApplicationComponent
import javax.inject.Inject




/**
 * Created by Adam on 6/4/2017.
 */
class SetListApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    var context: Context? = null


    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        DaggerApplicationComponent
                .builder()
                .application(this)
                .room(RoomModule())
                .applicationModule(ApplicationModule())
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}