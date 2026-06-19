package app.marlboroadvance.mpvex.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * Walks the [ContextWrapper] chain to find the underlying [Activity], or null
 * if no Activity is reachable. Use this from Compose via `LocalContext.current`
 * whenever you need to call Activity-only methods such as [Activity.finish].
 *
 * Why: `LocalContext.current` is frequently a ContextWrapper (e.g.
 * ContextThemeWrapper, MutableContextWrapper) rather than the Activity itself,
 * so a direct `context as? Activity` cast silently fails and the click appears
 * to do nothing.
 */
fun Context.findActivity(): Activity? {
    var ctx: Context = this
    while (ctx is ContextWrapper) {
        if (ctx is Activity) return ctx
        ctx = ctx.baseContext
    }
    return ctx as? Activity
}
