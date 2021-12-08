package com.example.ordernow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import java.util.*
import kotlin.system.exitProcess

class SecondContent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_content)
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