package uts.c14230260.paba_latihanrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class adapterRecView (private val listWayang: ArrayList<dcWayang>) : RecyclerView
    .Adapter<adapterRecView.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_recycler, parent, false)
        val view : View
        if (viewType == 1) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler2, parent, false)
        }
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val wayang = listWayang[position]
        holder._namaWayang.setText(wayang.nama)
        holder._karakterWayang.setText(wayang.karakter)
        holder._deskripsiWayang.setText(wayang.deskripsi)
        Log.d("TEST", wayang.foto)
        Picasso.get()
            .load(wayang.foto)
            .resize(200,200)
            .into(holder._gambarWayang)
        holder._gambarWayang.setOnClickListener {
//            Toast.makeText(holder.itemView.context, wayang.nama, Toast.LENGTH_LONG).show()
            onItemClickCallback.onItemClicked(listWayang[holder.adapterPosition])
        }
        holder._btnHapus.setOnClickListener {
            onItemClickCallback.delData(position)
        }
    }

    override fun getItemCount(): Int {
        return listWayang.size
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val _namaWayang = view.findViewById<TextView>(R.id.namaWayang)
        val _karakterWayang = view.findViewById<TextView>(R.id.karakterWayang)
        val _deskripsiWayang = view.findViewById<TextView>(R.id.deskripsiWayang)
        val _gambarWayang = view.findViewById<ImageView>(R.id.gambarWayang)
        val _btnHapus = view.findViewById<Button>(R.id.btnHapus)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            2
        } else {
            1
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: dcWayang)
        fun delData(pos: Int)
    }
}