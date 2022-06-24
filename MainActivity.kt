package edu.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brandon Young
 * Description: App 01 - MainActivity class
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO (suggested): get a reference to the "play" button and use it to set its "onClick" listener to MainActivity
        val btnPlay = findViewById<Button>(R.id.btnPlay)
        btnPlay.setOnClickListener {

            // need to actually use onClick

        }
    }

    // TODO (suggested): get the player's name, symbol, and the first move choice; pass info to the TicTacToe activity
    override fun onClick(p0: View?) {

        val editName = findViewById<EditText>(R.id.edtName)
        // This should link to the name edit

        val rgSymbolSelect: RadioGroup = findViewById(R.id.radioSymbol)
        val rgTurn: RadioGroup = findViewById(R.id.radioTurn)
        // These should link to each radio group

        val rbChosenSymbol : RadioButton = findViewById(rgSymbolSelect.checkedRadioButtonId)
        // id should be either 'radioNoughts' or 'radioCrosses'
        val rbChosenTurn : RadioButton = findViewById(rgTurn.checkedRadioButtonId)
        // id should be either 'buttonYes' (which is first) or 'buttonNo' (which is last)

        val intent = Intent(this, TicTacToeActivity::class.java)
        startActivity(intent)
        // This should start the tictactoe class i believe
    }


}