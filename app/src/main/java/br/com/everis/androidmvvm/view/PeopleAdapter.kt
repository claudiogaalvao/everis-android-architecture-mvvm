package br.com.everis.androidmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.everis.androidmvvm.R
import br.com.everis.androidmvvm.infrastructure.network.model.People
import kotlinx.android.synthetic.main.people_item.view.*

//DONE: 3 - Mover esta classe para o package view
class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val peoples = mutableListOf<People>()

    fun setData(list: List<People>){
        peoples.clear()
        peoples.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = peoples.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(peoples[position])
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(people: People){
            itemView.textview_name.text = people.name
            itemView.textview_craft.text = people.craft
        }
    }
}