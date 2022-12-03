package edu.farmingdale.alrajab.allplayers_friendslist.data

import com.google.firebase.database.Exclude

data class Contact(
    @get:Exclude
    var id: String? = null,
    var username: String? = null

) {
}