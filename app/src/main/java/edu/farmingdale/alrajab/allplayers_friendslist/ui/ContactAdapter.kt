package edu.farmingdale.alrajab.allplayers_friendslist.ui

import edu.farmingdale.alrajab.allplayers_friendslist.data.Contact
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.farmingdale.alrajab.allplayers_friendslist.databinding.RecyclerViewContactBinding

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

var contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(val binding: RecyclerViewContactBinding): RecyclerView.ViewHolder(binding.root){

    }
}