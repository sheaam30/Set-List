package setlist.shea.setlist.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import setlist.shea.setlist.R
import setlist.shea.setlist.main.di.MainActivityComponent
import javax.inject.Singleton




/**
 * Created by Adam on 7/4/2017.
 */
@Module(subcomponents = arrayOf(MainActivityComponent::class))
class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context : Context) : SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.shared_prefs), Context.MODE_PRIVATE)
    }
}