package `in`.creativelizard.shortinglist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.my_item_cell.view.*

class MyItemAdapter(private val arrayList: ArrayList<MyListItem>,
                    private val context: Context,
                    private val layout: Int) : RecyclerView.Adapter<MyItemAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyItemAdapter.ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

    }




    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: MyListItem) {
            itemView.tvTitle.text = item.name
            itemView.tvNumber.text = item.number

        }
    }
}