package setlist.shea.setlist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import setlist.shea.domain.di.CsvModule
import setlist.shea.setlist.main.di.MainActivityModule
import setlist.shea.setlist.main.mvp.MainActivity
import setlist.shea.setlist.setlist.SetListFragment
import setlist.shea.setlist.setlist.di.SetListActivityModule
import setlist.shea.setlist.songlist.di.SongListFragmentModule
import setlist.shea.setlist.songlist.SongListFragment


/**
 * Created by Adam on 7/4/2017.
 */
@Module
abstract class ActivityFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(SongListFragmentModule::class, CsvModule::class))
    internal abstract fun provideSetListFragment(): SongListFragment

    @ContributesAndroidInjector(modules = arrayOf(SetListActivityModule::class))
    internal abstract fun provideSetListActivity(): SetListFragment

}