package com.luisenricke.simpleroomapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luisenricke.simpleroomapp.adapter.ContactAdapter
import com.luisenricke.simpleroomapp.data.entity.Contact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf(
        Contact("test@test.com", "test", 1),
        Contact("luis@luis.com", "luis", 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lbl_title.text = "Simple database"

        recycler_contacts.apply {
            setHasFixedSize(true)
            adapter = ContactAdapter(list) { item ->
                Toast.makeText(this.context, "clicked ${item.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
