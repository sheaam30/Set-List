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
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.song_list.mvp.SongListContract
import setlist.shea.setlist.song_list.mvp.SongListPresenter
import java.util.concurrent.TimeUnit

/**
 * Created by Adam on 10/14/2017.
 */
open class ListPresenterTest {

    lateinit var songListPresenter: SongListContract.Presenter
    @Mock
    lateinit var songListView: SongListContract.View
    @Mock
    lateinit var songListRepository: SongListContract.Repository

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

        MockitoAnnotations.initMocks(this)

        songListPresenter = SongListPresenter(songListRepository, songListView)
    }

    @Test
    fun addSetListClickedTest() {
        songListPresenter.onAddListFabClicked()

        verify(songListView).showAddListDialog()
    }

    @Test
    fun addSetList() {
        given(songListRepository.addSetList(setList)).willReturn(Completable.complete())

        songListPresenter.addSetList(setList)

        verify(songListRepository).addSetList(setList)
        verify(songListView).showListState()
    }

    @Test
    fun loadSongsFromSetListTest() {
        val listOfSongs = mutableListOf(Song("Song1", "Artist1", "Genre1", setList))

        given(songListRepository.getSongsFromSetList(setList)).willReturn(Flowable.just(listOfSongs))

        songListPresenter.loadSongsFromSetList(setList)

        verify(songListView).showListState()
        verify(songListView).displaySongs(listOfSongs)
    }

    @Test
    fun loadSongsFromEmptySetListTest() {
        val listOfSongs = emptyList<Song>()

        given(songListRepository.getSongsFromSetList(setList)).willReturn(Flowable.just(listOfSongs))

        songListPresenter.loadSongsFromSetList(setList)

        verify(songListView, Mockito.never()).showListState()
        verify(songListView, Mockito.never()).displaySongs(listOfSongs)
    }

    @Test
    fun songsAddedTest() {
        val song = Song("Song1", "Artist1", "Genre1", setList)

        given(songListRepository.addSongToSetList(song)).willReturn(Completable.complete())
        given(songListRepository.setList).willReturn(setList)

        songListPresenter.songAdded(song.name, song.artist, song.genre)
    }
}