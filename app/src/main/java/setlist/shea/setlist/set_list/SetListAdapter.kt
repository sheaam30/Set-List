package setlist.shea.setlist.set_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.SwipeLayout
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R

/**
 * Created by adam on 12/29/17.
 */
class SetListAdapter(private var listActionListener: (SetList, SetListFragment.ListAction) -> Unit): RecyclerView.Adapter<SetListAdapter.SetListViewHolder>() {

    var setLists: List<SetList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SetListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_set_list, parent, false)
        val setListViewHolder = SetListViewHolder(view)

        setListViewHolder.bottomWrapper.setOnClickListener({ listActionListener(setLists[setListViewHolder.adapterPosition], SetListFragment.ListAction.Delete())})
        setListViewHolder.container.setOnClickListener({ listActionListener(setLists[setListViewHolder.adapterPosition], SetListFragment.ListAction.Click()) })
        return setListViewHolder
    }

    override fun onBindViewHolder(holder: SetListViewHolder?, position: Int) {
        holder?.setListName?.text = setLists[position].listName
        holder?.setListCount?.text = holder?.itemView?.context?.resources?.getQuantityString(R.plurals.songs,
                setLists[position].songs.count(),
                setLists[position].songs.count())
    }

    override fun getItemCount(): Int = setLists.size

    class SetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var swipeView: SwipeLayout = itemView.findViewById(R.id.swipe_view)
        var setListName: TextView = itemView.findViewById(R.id.setlist_name)
        val container: ViewGroup = itemView.findViewById(R.id.content)
        val bottomWrapper: ViewGroup = itemView.findViewById(R.id.bottom_wrapper)
        val setListCount: TextView = itemView.findViewById(R.id.setlist_count)

        init {
            swipeView.showMode = SwipeLayout.ShowMode.LayDown
        }
    }
}
