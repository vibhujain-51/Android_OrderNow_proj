package com.example.ordernow

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn:Button = findViewById(R.id.sign_up)
        val u_name:EditText = findViewById(R.id.user_name)
        val p_mail:EditText = findViewById(R.id.ph_mail)
        val pw:EditText = findViewById(R.id.pass_word)
        val gen1:RadioButton = findViewById(R.id.opt_male)
        val gen2:RadioButton = findViewById(R.id.opt_female)
        val spinner:Spinner = findViewById(R.id.country_list)
        val checkBox:CheckBox = findViewById(R.id.pass_show)

        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                pw.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            }
            else
                pw.setTransformationMethod(PasswordTransformationMethod.getInstance())

        }

        btn.setOnClickListener {
            val dat1: String = u_name.text.toString()
            val dat2 = p_mail.text.toString()
            val dat3 = pw.text.toString()
            var stat1 = "no"
            var stat2 = "no"
            if (gen1.isChecked) {stat1="yes"
                stat2 ="no"}
            else if (gen2.isChecked) {stat2="yes"
                stat1="no"}


            fun next_act(){
                val intent2 = Intent(this,UserScreen::class .java)
                startActivity(intent2)
            }


            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(adapterview: AdapterView<*>?, view: View?, position: Int, id: Long) {
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

            if(dat1 == "" || dat2=="" || dat3=="" || stat1== "no" && stat2 == "no"||spinner.selectedItem=="None") {
                    var snackbar = Snackbar.make(it,"Please fill all the details",Snackbar.LENGTH_INDEFINITE)
                    snackbar.setAction("OK", View.OnClickListener {
                        snackbar.dismiss()
                    })
                    snackbar.show()
            }
            else{
                var alert = AlertDialog.Builder(this)
                alert.setMessage("Registration Successfull")

                alert.setPositiveButton("OK"){ dialogInterface: DialogInterface, i: Int ->
                next_act()}.show()
            }
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


