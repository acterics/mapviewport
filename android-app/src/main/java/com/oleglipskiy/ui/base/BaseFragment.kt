package com.oleglipskiy.ui.base

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.oleglipskiy.di.DI
import toothpick.Scope
import toothpick.Toothpick

private const val STATE_SCOPE_NAME = "com.oleglipskiy.mapviewport.sample.base.STATE_SCOPE_NAME"

abstract class BaseFragment(@LayoutRes layoutRes: Int): Fragment(layoutRes) {

    private var instanceStateSaved: Boolean = false

    private val viewHandler = Handler()

    protected open val parentScopeName: String by lazy {
        (parentFragment as? BaseFragment)?.fragmentScopeName ?: DI.APP_SCOPE
    }

    private lateinit var fragmentScopeName: String
    protected lateinit var scope: Scope
        private set

    protected open fun installModules(scope: Scope) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentScopeName = savedInstanceState?.getString(STATE_SCOPE_NAME) ?: objectScopeName()
        if (Toothpick.isScopeOpen(fragmentScopeName)) {
            Log.d("Toothpick", "Get exist UI scope: $fragmentScopeName")
            scope = Toothpick.openScope(fragmentScopeName)
        } else {
            Log.d("Toothpick", "Init new UI scope: $fragmentScopeName from $parentScopeName")
            scope = Toothpick.openScopes(parentScopeName, fragmentScopeName)
            installModules(scope)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    protected fun postViewAction(action: () -> Unit) {
        viewHandler.post(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewHandler.removeCallbacksAndMessages(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
        outState.putString(STATE_SCOPE_NAME, fragmentScopeName)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (needCloseScope()) {
            Log.d("Toothpick", "Destroy UI scope: $fragmentScopeName")
            Toothpick.closeScope(scope.name)
        }
    }


    private fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) //because isRemoving == true for fragment in backstack on screen rotation
                || ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    protected fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    open fun onBackPressed() {}
}

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"