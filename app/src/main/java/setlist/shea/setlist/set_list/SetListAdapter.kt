package setlist.shea.setlist.set_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import setlist.shea.domain.model.SetList
import setlist.shea.setlist.R

/**
 * Created by adam on 12/29/17.
 */
class SetListAdapter: RecyclerView.Adapter<SetListAdapter.SetListViewHolder>() {

    var setLists: List<SetList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SetListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_set_list, parent, false)
        return SetListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetListViewHolder?, position: Int) {
        holder?.textView?.text = setLists[position].listName
    }

    override fun getItemCount(): Int = setLists.size

    class SetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView)
    }
}
