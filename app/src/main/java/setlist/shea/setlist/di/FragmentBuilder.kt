package setlist.shea.setlist.di

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import setlist.shea.setlist.list.SetListFragment
import setlist.shea.setlist.list.di.SetListFragmentComponent

/**
 * Created by Adam on 8/28/2017.
 */
@Module
abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(SetListFragment::class)
    internal abstract fun bindSetListFragment(builder: SetListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}