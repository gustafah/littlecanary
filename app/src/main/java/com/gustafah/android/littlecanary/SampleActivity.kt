package com.gustafah.android.littlecanary

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.gustafah.android.littlecanary.common.ext.addMaskedTextWatcher
import com.gustafah.android.littlecanary.watcher.*
import com.gustafah.android.littlecanarysample.R

class SampleActivity : AppCompatActivity(R.layout.sample_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<AppCompatButton>(R.id.btn_mask).setOnClickListener {
            addMasks()
        }
    }

    private fun addMasks() {
        findViewById<AppCompatEditText>(R.id.edt_cep).addMaskedTextWatcher(CEPMaskedTextWatcher {
            Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                .show()
        })
        findViewById<AppCompatEditText>(R.id.edt_cpf).addMaskedTextWatcher(
            CPFMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_cnpj).addMaskedTextWatcher(
            CNPJMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_phone).addMaskedTextWatcher(
            PhoneMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
        findViewById<AppCompatEditText>(R.id.edt_plate).addMaskedTextWatcher(
            PlateMaskedTextWatcher {
                Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

}