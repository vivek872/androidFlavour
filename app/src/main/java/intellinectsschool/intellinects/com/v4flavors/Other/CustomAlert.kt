package intellinectsschool.intellinects.com.v4flavors.Other

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import intellinectsschool.intellinects.com.v4flavors.R


/**
 * Modified by vikas 13/3/2018
 */
class CustomAlert {
    companion object {

        /**
         * Show dialog alert
         */
        fun show(context: Context, message: String) {

            var mDialog: Dialog? = null
            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(R.string.app_name)
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    .setIcon(R.drawable.logo)


            if  (mDialog != null) {
                mDialog.dismiss()
            }
            try {
                alertDialog.show();

            }catch (e: Exception )
            {
                e.printStackTrace()
            }

        }
    }
}