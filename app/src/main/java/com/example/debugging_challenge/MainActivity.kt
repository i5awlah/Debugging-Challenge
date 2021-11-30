package com.example.debugging_challenge

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var listsRecyclerView: RecyclerView
    private lateinit var fabButton: FloatingActionButton
    private lateinit var alertDialogSubmitBtn: Button
    private var arrayListOfCountriesAndCapitals = arrayListOf(
        arrayListOf("Saudi Arabia", "Riyadh"),
        arrayListOf("Nigeria", "Abuja"),
        arrayListOf("Rwanda", "Kigali"),
        arrayListOf("USA", "Washington"),
        arrayListOf("China", "Beijing"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabButton = findViewById(R.id.fabBtn)

        sharedPreferences = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        getListFromSharedPreferences()
        setupRecyclerView()


        fabButton.setOnClickListener {
            val singleUserEntryList = arrayListOf<String>()

            //AlertDialog
            val (dialogView, alertDialog) = setupAlertDialog()

            //Initialize edit texts
            val countryET = dialogView.findViewById<EditText>(R.id.countryEt)
            val capitalET = dialogView.findViewById<EditText>(R.id.capitalEt)




            alertDialogSubmitBtn.setOnClickListener {

                //Store user's input text to variables
                val countryText = countryET.text.toString()
                val capitalText = capitalET.text.toString()

                if (countryText.isEmpty() || capitalText.isEmpty()) {
                    Log.d("ABC","Empity")
                } else {
                    //Add both texts to list
                    singleUserEntryList.add(countryText)
                    singleUserEntryList.add(capitalText)

                    //Add single entry list to Global list
                    arrayListOfCountriesAndCapitals.add(singleUserEntryList)
                    saveListInSharedPreferences()
                }

                alertDialog.dismiss()
            }

        }
    }

    private fun setupAlertDialog(): Pair<View, AlertDialog> {
        //Inflate layout for Alert Dialog
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_layout, null)

        val builder =
            AlertDialog.Builder(this).setView(dialogView).setTitle("Country/Capital Form")
        val alertDialog = builder.show()
        alertDialogSubmitBtn = dialogView.findViewById(R.id.submitBtn)
        return Pair(dialogView, alertDialog)
    }

    private fun setupRecyclerView() {
        listsRecyclerView = findViewById(R.id.lists_recyclerview)
        listsRecyclerView.layoutManager = LinearLayoutManager(this)
        listsRecyclerView.adapter =
            ListSelectionRecyclerViewAdapter(this, arrayListOfCountriesAndCapitals)
    }

    fun getListFromSharedPreferences() {
        arrayListOfCountriesAndCapitals = convertStringToArray(sharedPreferences.getString("myList", "")!!)
    }

    fun saveListInSharedPreferences() {
        with(sharedPreferences.edit()) {
            putString("myList", convertArrayToString(arrayListOfCountriesAndCapitals))
            apply()
        }
    }

    fun convertArrayToString(arr: ArrayList<ArrayList<String>>) : String {
        var arrString = arrayListOf<String>()
        for (i in arr) {
            arrString.add( i.joinToString ("*") )
        }
        return arrString.joinToString (",")
    }
    fun convertStringToArray(str: String ) : ArrayList<ArrayList<String>> {
        val rows = str.split(",")

        val matrix = ArrayList<ArrayList<String>>()
        for (row in rows) {
            matrix.add(ArrayList(row.split("*")))
        }
        return matrix
    }


}

/*

1- The recycler view behaves unexpectedly. Each view takes up the entire screen.
(Change height to wrap_content)

2 The recycler view only shows the serial number and the capital of different countries. It should show the country and its capital.
(add another text view)

3 The app allows the user to submit the form with empty fields.
(check fields if empty before added in list)

The list starts at 0 instead of 1.
4 (change value of position by increased by 1)

5 When the user enters data in both fields of the alert dialog and hits submit, the recycler view is not updated with that information but the list serial number is increased. This shows the recycler view is updated but the information is not.
(change place of take data = after submit)
 */