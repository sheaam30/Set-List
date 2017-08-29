package setlist.shea.setlist.di

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import setlist.shea.setlist.main.MainActivity
import setlist.shea.setlist.main.di.MainActivityComponent


/**
 * Created by Adam on 7/4/2017.
 */
@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}