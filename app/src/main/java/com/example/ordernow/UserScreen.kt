package com.example.ordernow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.system.exitProcess

class UserScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_screen)

        val r1:Button = findViewById(R.id.read1)
        val r2:Button = findViewById(R.id.read2)
        val r3:Button = findViewById(R.id.read3)
        val r4:Button = findViewById(R.id.read4)

        val b1:Button = findViewById(R.id.buy1)
        val b2:Button = findViewById(R.id.buy2)
        val b3:Button = findViewById(R.id.buy3)
        val b4:Button = findViewById(R.id.buy4)



        r1.setOnClickListener {
            val intent = Intent(this,FirstContent::class .java)
            startActivity(intent)
        }

        r2.setOnClickListener {
            val intent = Intent(this,SecondContent::class .java)
            startActivity(intent)
        }

        r3.setOnClickListener {
            val intent = Intent(this,ThirdContent::class .java)
            startActivity(intent)
        }

        r4.setOnClickListener {
            val intent = Intent(this,FourthContent::class .java)
            startActivity(intent)
        }

        b1.setOnClickListener {
            var snackbar = Snackbar.make(it,"This Feature is Coming Soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
        b2.setOnClickListener {
            var snackbar = Snackbar.make(it,"This Feature is Coming Soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
        b3.setOnClickListener {
            var snackbar = Snackbar.make(it,"This Feature is Coming Soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
        b4.setOnClickListener {
            var snackbar = Snackbar.make(it, "This Feature is Coming Soon", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar,menu)
        return true
    }

    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    private fun change_lang() {
        val langs = arrayOf("English", "हिंदी (Hindi)", "ਪੰਜਾਬੀ (Punjabi)", "русский (Russian)")
        val bld = AlertDialog.Builder(this)
        bld.setTitle("Choose Language")
        bld.setSingleChoiceItems(langs, -1) { dialog, which ->
            if (which == 0) {
                setAppLocale(this, "en")
                recreate()
            } else if (which == 1) {
                setAppLocale(this, "hi")
                recreate()
            } else if (which == 2) {
                setAppLocale(this, "pa")
                recreate()
            } else if (which == 3) {
                setAppLocale(this, "ru")
                recreate()
            }
            dialog.dismiss()
        }
        bld.show()
    }

    fun exit_app(){
        moveTaskToBack(true)
        exitProcess(-1)
    }

    fun share_app(){
        val message= "This is very Useful app you should also use it its name is Order Now"
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent,"Choose any app to share"))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.chng_lang ->change_lang()
            R.id.close_app -> exit_app()
            R.id.sharing -> share_app()
        }
        return true
    }

}