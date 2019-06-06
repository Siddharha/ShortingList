package `in`.creativelizard.shortinglist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import android.R.attr.data
import java.util.*
import java.util.Collections
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var myItems:ArrayList<MyListItem>
    lateinit var myItemAdapter: MyItemAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        loadData()
    }

    private fun loadData() {
        val jData = JSONObject(AppUtil.loadJSONFromAsset(this,"data.json")).getJSONObject("data")
        Log.e("data",jData.toString())

        if(jData.getBoolean("success")){
            val jsonArrayList = jData.getJSONArray("itemList")
            for(i in 0 until jsonArrayList.length()){
                val itmJson = jsonArrayList.getJSONObject(i)
                val itm = Gson().fromJson<MyListItem>(itmJson.toString(),MyListItem::class.java)
                myItems.add(itm)
            }

            myItems.sortWith(Comparator { lhs, rhs ->
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (lhs.name > rhs.name) -1 else if (lhs.id < rhs.id) 1 else 0
            })
            myItemAdapter.notifyDataSetChanged()

        }

    }

    private fun initialize() {
        myItems = ArrayList()
        myItemAdapter = MyItemAdapter(myItems,this,R.layout.my_item_cell)
        layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        rlItems.layoutManager = layoutManager
        rlItems.adapter = myItemAdapter

    }
}
