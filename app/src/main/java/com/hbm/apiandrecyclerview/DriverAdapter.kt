package com.hbm.apiandrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DriverAdapter(private var driversList: List<Driver>, private val onItemClick: (Driver) -> Unit) :
    RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedDriver = driversList[position]
                    onItemClick(selectedDriver)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_driver, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDriver = driversList[position]
        holder.nameTextView.text = "${currentDriver.givenName} ${currentDriver.familyName}"
        //holder.numberTextView.text = "Number: ${currentDriver.permanentNumber}"
        //holder.nationalityTextView.text = "Nationality: ${currentDriver.nationality}"
        //holder.dobTextView.text = "DOB: ${currentDriver.dateOfBirth}"
    }

    override fun getItemCount(): Int {
        return driversList.size
    }

    fun filterList(filteredList: List<Driver>) {
        driversList = filteredList
        notifyDataSetChanged()
    }
}

