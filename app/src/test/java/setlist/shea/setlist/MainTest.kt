package setlist.shea.setlist

import android.support.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import setlist.shea.setlist.main.mvp.MainActivity
import setlist.shea.setlist.main.mvp.MainContract
import setlist.shea.setlist.main.mvp.MainPresenter
import setlist.shea.setlist.main.mvp.MainRepository
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import setlist.shea.domain.model.SetList
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import java.util.concurrent.TimeUnit


/**
 * Created by Adam on 10/14/2017.
 */
open class MainTest {

    @Mock
    lateinit var mainView: MainContract.View
    @Mock
    lateinit var mainRepository : MainContract.Repository

    lateinit var mainPresenter : MainContract.Presenter

    companion object {
        @BeforeClass
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
    }

    @Before
    fun setup() {
        beforeClass()
        mainView = Mockito.mock(MainActivity::class.java)
        mainRepository = Mockito.mock(MainRepository::class.java)

        mainPresenter = MainPresenter(mainRepository, mainView)
    }

    @Test
    fun loadSetListTitlesTest() {
        val setList = emptyList<SetList>()
        given(mainRepository.getSetListTitles()).willReturn(Single.just(setList))

        mainPresenter.loadSetListTitles()

        verify(mainRepository).getSetListTitles()
        verify(mainView).showLoadDialog(setList)
    }

    @Test
    fun loadSetListTest() {
        val setList = SetList("MySetList")

        mainPresenter.loadSetList(setList)

        verify(mainRepository).setCurrentSetList(setList.listName)
        verify(mainView).showList(setList)
    }
}