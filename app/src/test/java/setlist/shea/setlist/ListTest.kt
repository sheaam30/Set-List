package setlist.shea.setlist

import android.support.annotation.NonNull
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.list.mvp.*
import java.util.concurrent.TimeUnit

/**
 * Created by Adam on 10/14/2017.
 */
open class ListTest {

    lateinit var setListPresenter : SetListContract.Presenter
    @Mock
    lateinit var setListView : SetListContract.View
    @Mock
    lateinit var setListRepository : SetListContract.Repository

    fun beforeClass() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker = ExecutorScheduler.ExecutorWorker(({ it.run() }))
        }

        RxJavaPlugins.setInitIoSchedulerHandler({ scheduler -> immediate })
        RxJavaPlugins.setInitComputationSchedulerHandler({ scheduler -> immediate })
        RxJavaPlugins.setInitNewThreadSchedulerHandler({ scheduler -> immediate })
        RxJavaPlugins.setInitSingleSchedulerHandler({ scheduler -> immediate })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Before
    fun setup() {
        beforeClass()
        setListView = Mockito.mock(SetListFragment::class.java)
        setListRepository = Mockito.mock(SetListRepository::class.java)

        setListPresenter = SetListPresenter(setListRepository, setListView)
    }

    @Test
    fun addSetListClickedTest() {
        setListPresenter.onAddListFabClicked()

        verify(setListView).showAddListDialog()
    }

    @Test
    fun addSetList() {
        val setList = SetList("SetList")
        given(setListRepository.addSetList(setList)).willReturn(Completable.complete())

        setListPresenter.addSetList(setList)

        verify(setListRepository).addSetList(setList)
        verify(setListView).showListState()
    }
}