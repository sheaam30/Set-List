package setlist.shea.setlist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import setlist.shea.domain.di.RoomModule
import setlist.shea.setlist.SetListApp
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RoomModule::class,
        ApplicationModule::class,
        ActivityFragmentProvider::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        @BindsInstance fun room(roomModule: RoomModule): Builder
        @BindsInstance fun applicationModule(applicationModule: ApplicationModule): Builder
        fun build(): ApplicationComponent
    }

    fun inject(setListApp: SetListApp)
}