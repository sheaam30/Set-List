package setlist.shea.setlist.list.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import setlist.shea.domain.di.CsvModule
import setlist.shea.setlist.di.FragmentScope
import setlist.shea.setlist.list.SetListFragment

/**
 * Created by Adam on 8/28/2017.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(SetListFragmentModule::class, CsvModule::class))
interface SetListFragmentComponent : AndroidInjector<SetListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SetListFragment>()
}