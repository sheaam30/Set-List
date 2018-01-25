package setlist.shea.setlist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import setlist.shea.domain.di.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RoomModule::class,
        ApplicationModule::class,
        ActivityFragmentProvider::class,
        AndroidSupportInjectionModule::class,
        StoreModule::class))
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        @BindsInstance fun room(roomModule: RoomModule): Builder
        @BindsInstance fun applicationModule(applicationModule: ApplicationModule): Builder
        @BindsInstance fun storeModule(storeModule: StoreModule): Builder
        fun build(): ApplicationComponent
    }
}