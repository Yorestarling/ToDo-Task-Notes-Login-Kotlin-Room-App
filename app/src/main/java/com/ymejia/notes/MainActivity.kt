package com.ymejia.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ymejia.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NoteClickDeleteInterface,NoteClickInterface {

    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var binding: ActivityMainBinding
    lateinit var viewModal: NoteViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        notesRV = binding.idnotesRV
        addFAB = binding.idFAB
        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this,this,this)
        notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModal::class.java)
        viewModal.allNotes.observe(this, Observer { list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val intent = Intent(this@MainActivity,AddNotesActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(note:Note){
        viewModal.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note:Note){
        val intent = Intent(this@MainActivity,AddNotesActivity::class.java)
        intent.putExtra("NoteType","Edit")
        intent.putExtra("NoteTitle",note.noteTitle)
        intent.putExtra("NoteDescription",note.noteDescription)
        intent.putExtra("NoteID",note.id)
        startActivity(intent)
        this.finish()

    }

}