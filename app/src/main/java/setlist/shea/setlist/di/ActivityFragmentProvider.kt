package setlist.shea.setlist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import setlist.shea.domain.di.CsvModule
import setlist.shea.setlist.list.SetListFragment
import setlist.shea.setlist.list.di.SetListFragmentModule
import setlist.shea.setlist.main.MainActivity
import setlist.shea.setlist.main.di.MainActivityModule


/**
 * Created by Adam on 7/4/2017.
 */
@Module
abstract class ActivityFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun provideMainActivity(): MainActivity


    @ContributesAndroidInjector(modules = arrayOf(SetListFragmentModule::class, CsvModule::class))
    internal abstract fun provideSetListFragment(): SetListFragment
}