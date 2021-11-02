package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter:TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val onLongClickListener=object:TaskItemAdapter.OnLongClickListener{
            override fun OnItemLongClicked(position: Int) {
                listOfTasks.removeAt(position)
                adapter.notifyDataSetChanged()
                saveItems()
            }
        }

        loadItems()

        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTasks,onLongClickListener)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)

        val inputText=findViewById<EditText>(R.id.addTaskField)
        //set up button and input field
        findViewById<Button>(R.id.button).setOnClickListener{
            val userInputtedTask = inputText.text.toString()
            listOfTasks.add(userInputtedTask)
            adapter.notifyItemInserted(listOfTasks.size-1)
            inputText.setText("")
            saveItems()
        }
    }
    //save data by writing and reading from a file
    //create method to get file
    fun getDataFile() : File {
        return File (filesDir,"data.txt")
    }
    //load items line by line
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException:IOException) {
            ioException.printStackTrace()
        }
    }
    //save items
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException:IOException) {
            ioException.printStackTrace()
        }
    }
}