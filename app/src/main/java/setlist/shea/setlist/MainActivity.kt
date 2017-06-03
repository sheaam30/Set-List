package setlist.shea.setlist

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.shea.mvp.activity.BaseActivity

class MainActivity : BaseActivity<MainPresenter>() {

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun createPresenter(restoredBundle: Bundle?) {
        presenter = MainPresenter(MainInteractor(), MainView(this))
    }

    override val layoutId: Int
        get() = R.layout.activity_main //To change initializer of created properties use File | Settings | File Templates.
}
