package setlist.shea.setlist.main.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import setlist.shea.domain.di.CsvModule
import setlist.shea.setlist.di.ActivityScope
import setlist.shea.setlist.main.MainActivity

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class, CsvModule::class))
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}