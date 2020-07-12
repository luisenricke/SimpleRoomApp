package com.luisenricke.simpleroomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisenricke.simpleroomapp.R
import com.luisenricke.simpleroomapp.data.entity.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(val contacts: List<Contact>, val clickListener: (Contact) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false))

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) = holder.bind(contacts[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contact: Contact) {
            with(itemView) {
                lbl_id.text = contact.id.toString()
                lbl_name.text = contact.name
                lbl_name.text = contact.email

                btn_delete.setOnClickListener { }
                setOnClickListener { clickListener(contact) }
            }
        }
    }
}
