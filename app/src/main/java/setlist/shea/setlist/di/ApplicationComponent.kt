package setlist.shea.setlist.di

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import setlist.shea.domain.di.RoomModule
import setlist.shea.setlist.SetListApp
import javax.inject.Singleton
import dagger.BindsInstance
import setlist.shea.setlist.main.MainInteractor

@Singleton
@Component(modules = arrayOf(RoomModule::class,
        ApplicationModule::class,
        ActivityBuilder::class))
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