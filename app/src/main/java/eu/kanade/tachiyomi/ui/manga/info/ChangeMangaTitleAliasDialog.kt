package eu.kanade.tachiyomi.ui.manga.info

import android.app.Dialog
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.bluelinelabs.conductor.Controller
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.base.controller.DialogController

/**
 * Dialog to change the alias name for the manga title.
 */
class ChangeMangaTitleAliasDialog<T>(bundle: Bundle? = null) : DialogController(bundle)
        where T : Controller, T : ChangeMangaTitleAliasDialog.Listener {

    /**
     * Name of the alias. Value updated with each input from the user.
     */
    private var currentTitleAlias = ""

    constructor(target: T, alias: String) : this() {
        targetController = target
        currentTitleAlias = alias
    }

    /**
     * Called when creating the dialog for this controller.
     *
     * @param savedViewState The saved state of this dialog.
     * @return a new dialog instance.
     */
    override fun onCreateDialog(savedViewState: Bundle?): Dialog {
        return MaterialDialog.Builder(activity!!)
                .title(R.string.action_edit_title_alias)
                .negativeText(android.R.string.cancel)
                .alwaysCallInputCallback()
                .input(resources?.getString(R.string.name), currentTitleAlias, true) { _, input ->
                    currentTitleAlias = input.toString()
                }
                .onPositive { _, _ -> (targetController as? Listener)?.setTitleAlias(currentTitleAlias) }
                .build()
    }

    interface Listener {
        fun setTitleAlias(name: String)
    }

}