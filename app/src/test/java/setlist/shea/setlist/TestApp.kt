package setlist.shea.setlist

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import setlist.shea.domain.di.RoomModule
import setlist.shea.setlist.di.ApplicationModule
import setlist.shea.setlist.di.DaggerApplicationComponent

/**
 * Created by Adam on 10/14/2017.
 */
class TestApp : SetListApp() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .room(RoomModule())
                .applicationModule(ApplicationModule())
                .build()
    }
}