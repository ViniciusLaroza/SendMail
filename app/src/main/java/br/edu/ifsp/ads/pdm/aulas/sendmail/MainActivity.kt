package br.edu.ifsp.ads.pdm.aulas.sendmail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import br.edu.ifsp.ads.pdm.aulas.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.clearBtn.setOnClickListener {
            amb.toEt.setText("")
            amb.ccEt.setText("")
            amb.bccEt.setText("")
            amb.subjectEt.setText("")
            amb.messageEt.setText("")
        }

        amb.sendBtn.setOnClickListener {
            val sendMailIntent = Intent(Intent.ACTION_SENDTO)

            with(sendMailIntent) {
                putExtra(Intent.EXTRA_EMAIL, arrayOf(amb.toEt.text.toString()))
                putExtra(Intent.EXTRA_CC, arrayOf(amb.ccEt.text.toString()))
                putExtra(Intent.EXTRA_BCC, arrayOf(amb.bccEt.text.toString()))
                putExtra(Intent.EXTRA_SUBJECT, arrayOf(amb.subjectEt.text.toString()))
                putExtra(Intent.EXTRA_TEXT, arrayOf(amb.messageEt.text.toString()))
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }

            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            chooserIntent.putExtra(Intent.EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}