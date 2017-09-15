package setlist.shea.setlist.list

import android.os.Bundle
import com.shea.mvp.fragment.BaseFragment
import dagger.android.support.AndroidSupportInjection
import setlist.shea.setlist.R
import javax.inject.Inject



/**
 * Created by Adam on 8/28/2017.
 */
class SetListFragment : BaseFragment<SetListInterface.ListPresenterInterface>() {

    private val SONGS_KEY = "songs"

    @Inject
    lateinit var setListPresenterInterface: SetListInterface.ListPresenterInterface

    init {
        arguments?.get(SONGS_KEY)
    }

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(): SetListFragment {
            val newsFragment = SetListFragment()
            val args = Bundle()
            newsFragment.arguments = args
            return newsFragment
        }
    }

    override fun getPresenter(): SetListInterface.ListPresenterInterface {
        return setListPresenterInterface
    }

    override val layoutId: Int
        get() = R.layout.fragment_list
}