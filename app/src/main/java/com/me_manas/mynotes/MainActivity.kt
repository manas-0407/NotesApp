package com.me_manas.mynotes

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*


class MainActivity : AppCompatActivity(), InterNotesRVAdapt {

    lateinit var viewModel: NoteViewModel
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDrawerLayout()

//        //switch button
//        val switch : SwitchMaterial = findViewById(R.id.nightswitch)
////        Objects.requireNonNull(getSupportActionBar())?.setTitle("Light-Night MODE SWITCH")
//
//        switch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//            // do something, the isChecked will be
//            // true if the switch is in the On position
//            if(isChecked){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                buttonView.setText("NIGHT MODE")
//            }
//            else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                buttonView.setText("LIGHT MODE")
//            }
//        })
//
//        val isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
//        switch.setChecked(isNightModeOn)
//        if(isNightModeOn){
//            switch.setText("NIGHT MODE")
//        }
//        else{
//            switch.setText("LIGHT MODE")
//        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

    viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allnotes.observe(this, Observer { list->
            list?.let{
                adapter.updateList(it)
                recyclerView.smoothScrollToPosition(adapter.itemCount)
            }
        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deletenote(note)
        Toast.makeText(this, "NOTE DELETED",Toast.LENGTH_SHORT).show()
    }

//    fun submitData(view: View) {
//        val inputeditext= findViewById<EditText>(R.id.edittext)
//        val noteText = inputeditext.text.toString()
//        if(noteText.isNotEmpty()){
//            viewModel.insertnote(Note(noteText))
//            Toast.makeText(this, "NOTE INSERTED",Toast.LENGTH_SHORT).show()
//
//        }
//    }

    fun setUpDrawerLayout(){
        setSupportActionBar(findViewById(R.id.topAppBar))
        actionBarDrawerToggle = ActionBarDrawerToggle(this,findViewById(R.id.drawerlayout),R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    fun newNote(view: View){
        val intent:Intent = Intent(this,newNote::class.java)
        startActivity(intent)
    }
}