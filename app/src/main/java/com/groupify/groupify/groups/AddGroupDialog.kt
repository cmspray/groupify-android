package com.groupify.groupify.groups

import android.app.AlertDialog
import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.os.Bundle
import android.widget.EditText
import com.groupify.groupify.R
import com.groupify.groupify.retrofit.RetrofitHelper

class AddGroupDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = AlertDialog.Builder(activity).setView(activity?.layoutInflater?.inflate(R.layout.add_group_dialog, null)).setPositiveButton(R.string.add, { dialog, id ->
        onAdd(this@AddGroupDialog.dialog.findViewById<EditText>(R.id.group_name_editable).text.toString())
    }).setNegativeButton(R.string.cancel, { dialog, which ->
        this@AddGroupDialog.dialog.cancel()
    }).create()

    private fun onAdd(groupName: String) {
        RetrofitHelper.addGroup(activity, groupName)
    }

    companion object {
        val TAG: String = "AddGroupDialog"
    }
}
