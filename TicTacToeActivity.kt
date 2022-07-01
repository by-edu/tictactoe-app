package edu.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brandon Young
 * Description: App 01 - TicTacToe Activity class
 */

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class TicTacToeActivity : AppCompatActivity(),View.OnClickListener, Serializable {

    // TODOd (suggested): maintain a reference to a TicTacToe object
    var tictactoe : TicTacToe? = null

    private var buttons = arrayOf<Array<Button?>>(arrayOfNulls<Button>(3), arrayOfNulls<Button>(3), arrayOfNulls<Button>(3))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictactoe)

        // TODOd (suggested): get the player's name, symbol, and the first move choice from the activity's intent; use them to instantiate a TicTacToe game; decide whether the computer should play based on the first move choice
        val grabbedName = intent.getStringExtra("name")
        val playerSymbol = intent.getCharExtra("symbol", ' ')
        val playerTurn = intent.getBooleanExtra("turn", false)

        tictactoe = TicTacToe(grabbedName.toString(), playerSymbol)

        // TODOd (suggested): get a reference to the TextView "player info" area; update the TextView with the player's name and symbol
        val playerInfo : TextView = findViewById(R.id.playerInfo)
        playerInfo.setText("Player $grabbedName is using $playerSymbol")

        // TODOd (suggested): using a loop and button tags, update their texts and "onClick" listeners to TicTacToeActivity; remember to disable the button if it corresponds to a computer's first move
        // hint: use "findViewWithTag"
        for (i in 0..2 ) {
            for(j in 0..2){
                var btnName : String = "btn$i$j"
                var btnID : Int = resources.getIdentifier(btnName, "id", packageName)

                buttons[i][j] = findViewById(btnID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }

        if (!playerTurn){
            tictactoe?.computerPlay()
            val split = tictactoe?.lastMove

            buttons[split?.first!!][split?.second!!]!!.isEnabled = false
            buttons[split?.first!!][split?.second!!]!!.text = tictactoe?.getComputerSymbol().toString()

        }

    }

    // TODOd (suggested): display a Toast with a text based on the game's result
    fun showResults() {

        val result = if (tictactoe?.getWinner() == tictactoe?.playerSymbol){
            Toast.makeText(this, "You won; congratulations!!!", Toast.LENGTH_SHORT).show()
        } else if (tictactoe?.getWinner() == tictactoe?.getComputerSymbol()) {
            Toast.makeText(this, "I won; computers are superior", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tie!!!", Toast.LENGTH_SHORT).show()
        }
    }

    // TODO (suggested): cast the given view as a Button; disable the button so you don't forget; get the button's tag and use it to infer the player's move coordinates; make the move and update the button's text with the player's symbol; if the game is over, show results; otherwise, have the computer play; use TitTacToe's last move and "findViewWithTag" to get a reference to the button of the computer's play; disable the button so you don't forget; update the button's text with the computer's symbol; if the game is over, show results
    override fun onClick(view: View?) {

        var btnName : String = resources.getResourceName(view!!.id)
        var btnLoc : String = btnName.substring(btnName.lastIndexOf("n") + 1)
        var i: Int = btnLoc.get(0).toString().toInt()
        var j: Int = btnLoc.get(1).toString().toInt()

        (view as Button)
        if (view.text != null){
            view.isEnabled = false
        }

        if (tictactoe?.playerPlay(i, j) == true){
            view.isEnabled = false
            view.text = tictactoe?.playerSymbol.toString()
        }
        if (tictactoe?.isGameOver() == true){
            showResults()
            } else {
                tictactoe?.computerPlay()
                buttons[tictactoe?.lastMove?.first!!][tictactoe?.lastMove?.second!!]!!.isEnabled = false
                buttons[tictactoe?.lastMove?.first!!][tictactoe?.lastMove?.second!!]!!.text = tictactoe?.getComputerSymbol().toString()
            }
        if (tictactoe?.isGameOver() == true){
            showResults()
            }
        }



    // TODOd: save the TicTacToe object using the outState Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("tictactoe", tictactoe)
    }

    // TODOd: restore the TicTacToe object using the savedInstanceState Bundle
    override fun onRestoreInstanceState(savedIntanceState: Bundle) {
        super.onRestoreInstanceState(savedIntanceState)
        tictactoe = savedIntanceState.getSerializable("tictactoe") as TicTacToe?
    }
}

