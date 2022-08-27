package com.example.usinggithubjsonforgettingmasterdata

import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun MaterialAlertDialogBuilder.setMessage(it: MasterData): MaterialAlertDialogBuilder {
    return if (it.forceUpdate) {
        this.setMessage(it.forceUpdateMessage)
    } else {
        this.setMessage(it.updateMessage)
    }
}

fun MaterialAlertDialogBuilder.setNegativeButton(isForceUpdate: Boolean): MaterialAlertDialogBuilder {
    return if (isForceUpdate) {
        this
    } else {
        this.setNegativeButton(R.string.label_skip) { _, _ -> }
    }
}