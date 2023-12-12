// MainActivity.kt
package com.daman.task_9

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: CustomAdapter
    private lateinit var data: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        val addButton: Button = findViewById(R.id.addButton)

        data = mutableListOf("Item 1", "Item 2", "Item 3") // Initial data

        adapter = CustomAdapter(this, data)
        listView.adapter = adapter

        addButton.setOnClickListener {
            showAddItemDialog()
        }
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        val editTextName: EditText = dialogView.findViewById(R.id.editTextName)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val newItem = editTextName.text.toString()
                if (newItem.isNotEmpty()) {
                    data.add(newItem)
                    adapter.notifyDataSetChanged()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}
