package setlist.shea.setlist

import android.support.annotation.NonNull
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
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

    private val setList = SetList("SetList")

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
        given(setListRepository.addSetList(setList)).willReturn(Completable.complete())

        setListPresenter.addSetList(setList)

        verify(setListRepository).addSetList(setList)
        verify(setListView).showListState()
    }

    @Test
    fun loadSongsFromSetListTest() {
        val listOfSongs = mutableListOf(Song("Song1", "Artist1", "Genre1", setList))

        given(setListRepository.getSongsFromSetList(setList)).willReturn(Flowable.just(listOfSongs))

        setListPresenter.loadSongsFromSetList(setList)

        verify(setListView).showListState()
        verify(setListView).displaySongs(listOfSongs)
    }

    @Test
    fun loadSongsFromEmptySetListTest() {
        val listOfSongs = emptyList<Song>()

        given(setListRepository.getSongsFromSetList(setList)).willReturn(Flowable.just(listOfSongs))

        setListPresenter.loadSongsFromSetList(setList)

        verify(setListView, Mockito.never()).showListState()
        verify(setListView, Mockito.never()).displaySongs(listOfSongs)
    }

    @Test
    fun songsAddedTest() {
        val song = Song("Song1", "Artist1", "Genre1", setList)

        given(setListRepository.addSongToSetList(song)).willReturn(Completable.complete())
        given(setListRepository.setList).willReturn(setList)

        setListPresenter.songAdded(song.name, song.artist, song.genre)
    }
}