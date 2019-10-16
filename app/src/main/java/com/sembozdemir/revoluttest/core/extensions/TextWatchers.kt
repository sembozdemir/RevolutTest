package com.sembozdemir.revoluttest.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEBOUNCE_TIME = 500L

fun EditText.doAfterTextChangedDebounced(
    debounceTime: Long = DEBOUNCE_TIME,
    initialSearchText: String = "",
    action: suspend CoroutineScope.(searchText: String) -> Unit
) {

    this.addTextChangedListener(object : TextWatcher {

        private var searchFor = initialSearchText

        override fun afterTextChanged(s: Editable?) {

            val searchText = s.toString().trim()

            if (searchText == searchFor) {
                return
            }

            searchFor = searchText

            CoroutineScope(Dispatchers.Main).launch {
                delay(debounceTime)

                if (searchText != searchFor) {
                    return@launch
                }

                action(this, searchText)
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}