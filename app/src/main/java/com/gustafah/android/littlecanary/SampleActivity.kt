package com.gustafah.android.littlecanary

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.gustafah.android.littlecanary.watcher.*
import com.gustafah.android.littlecanarysample.R

class SampleActivity : AppCompatActivity(R.layout.sample_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<AppCompatEditText>(R.id.edt_cep).addTextChangedListener(CEPMaskedTextWatcher {
            Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                .show()
        })
        findViewById<AppCompatEditText>(R.id.edt_cpf).addTextChangedListener(
            CPFMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_cnpj).addTextChangedListener(
            CNPJMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_phone).addTextChangedListener(
            PhoneMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_plate).addTextChangedListener(
            PlateMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

}