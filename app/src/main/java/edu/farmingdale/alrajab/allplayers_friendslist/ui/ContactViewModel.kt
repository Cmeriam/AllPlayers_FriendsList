package edu.farmingdale.alrajab.allplayers_friendslist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import edu.farmingdale.alrajab.allplayers_friendslist.data.Contact
import edu.farmingdale.alrajab.allplayers_friendslist.data.NODE_CONTACTS

class ContactViewModel: ViewModel() {

    private val dbcontacts = FirebaseDatabase.getInstance().getReference(NODE_CONTACTS)

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> get() = _contact

    fun addContact(contact: Contact){
        contact.id = dbcontacts.push().key

        dbcontacts.child(contact.id!!).setValue(contact).addOnCompleteListener{
            if(it.isSuccessful){
                _result.value = null
            }else{
                _result.value = it.exception
            }
        }
    }

    private val childEventListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val contact = snapshot.getValue(Contact::class.java)
            contact?.id = snapshot.key
            _contact.value = contact!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val contact = snapshot.getValue(Contact::class.java)
            contact?.id = snapshot.key
            _contact.value = contact!!
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val contact = snapshot.getValue(Contact::class.java)
            contact?.id = snapshot.key
            contact?.isDeleted = true
            _contact.value = contact!!
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    fun updateContact(contact: Contact){
        dbcontacts.child(contact.id!!).setValue(contact)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    _result.value = null
                }else{
                    _result.value = it.exception
                }
            }
    }

    fun deleteContact(contact: Contact){
        dbcontacts.child(contact.id!!).setValue(null)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    _result.value = null
                }else{
                    _result.value = it.exception
                }
            }
    }

    fun getRealtimeUpdate(){
        dbcontacts.addChildEventListener(childEventListener)
    }

    override fun onCleared() {
        super.onCleared()
        dbcontacts.removeEventListener(childEventListener)
    }

}