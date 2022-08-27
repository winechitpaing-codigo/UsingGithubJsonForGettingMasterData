package com.example.usinggithubjsonforgettingmasterdata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.usinggithubjsonforgettingmasterdata.event.observeEvent
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.uiEvent.observeEvent(this) {
            MaterialAlertDialogBuilder(this)
                .setTitle(it.updateTitle)
                .setMessage(it)
                .setCancelable(!it.forceUpdate)
                .setPositiveButton(getString(R.string.label_update)) { _, _ ->
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(it.storeLink)
                    })
                }
                .setNegativeButton(it.forceUpdate)
                .show()
        }
    }
}
