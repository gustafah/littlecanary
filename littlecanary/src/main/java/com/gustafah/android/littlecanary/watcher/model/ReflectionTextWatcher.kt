package com.gustafah.android.littlecanary.watcher.model

import android.widget.EditText
import java.lang.reflect.Field

object ReflectionTextWatcher {
    fun removeAll(editText: EditText) {
        try {
            val field: Field? = findField("mListeners", editText::class.java)
            if (field != null) {
                field.isAccessible = true
                val list = field[editText] as ArrayList<*>
                list.clear()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findField(name: String, type: Class<*>): Field? {
        for (declaredField in type.declaredFields) {
            if (declaredField.name.equals(name)) {
                return declaredField
            }
        }
        return if (type.superclass != null) {
            findField(name, type.superclass)
        } else null
    }
}