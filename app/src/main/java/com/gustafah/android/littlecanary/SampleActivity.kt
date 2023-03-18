package com.gustafah.android.littlecanary

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.gustafah.android.littlecanary.common.ext.setMaskedTextWatcher
import com.gustafah.android.littlecanary.watcher.*
import com.gustafah.android.littlecanarysample.R

class SampleActivity : AppCompatActivity(R.layout.sample_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<AppCompatButton>(R.id.btn_mask).setOnClickListener {
            addMasks(false)
        }
        findViewById<AppCompatButton>(R.id.btn_mask_filler).setOnClickListener {
            addMasks(true)
        }
    }

    private fun addMasks(shouldFill: Boolean) {
        findViewById<AppCompatEditText>(R.id.edt_cep).apply {
            setText("")
            setMaskedTextWatcher(
                CEPMaskedTextWatcher {
                    Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                        .show()
                }, shouldFillBlanks = shouldFill
            )
        }
        findViewById<AppCompatEditText>(R.id.edt_cpf).apply {
            setText("")
            setMaskedTextWatcher(
                CPFMaskedTextWatcher {
                    Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                        .show()
                }, shouldFillBlanks = shouldFill
            )
        }
        findViewById<AppCompatEditText>(R.id.edt_cnpj).apply {
            setText("")
            setMaskedTextWatcher(
                CNPJMaskedTextWatcher {
                    Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                        .show()
                }, shouldFillBlanks = shouldFill
            )
        }
        findViewById<AppCompatEditText>(R.id.edt_phone).apply {
            setText("")
            setMaskedTextWatcher(
                PhoneMaskedTextWatcher {
                    Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                        .show()
                }, shouldFillBlanks = shouldFill
            )
        }
        findViewById<AppCompatEditText>(R.id.edt_plate).apply {
            setText("")
            setMaskedTextWatcher(
                PlateMaskedTextWatcher {
                    Toast.makeText(this@SampleActivity, "isValid: $it", Toast.LENGTH_SHORT)
                        .show()
                }, shouldFillBlanks = shouldFill
            )
        }
    }

}