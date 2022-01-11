package com.iagora.wingman.commons.ui.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun <T> Flow<T>.collectWhenStarted(
    lifecycleOwner: LifecycleOwner,
    crossinline action: suspend (value: T) -> Unit,
) {
    lifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        collect(action)
    }
}

inline fun <T> Flow<T>.collectWithDelay(
    lifecycleOwner: LifecycleOwner,
    time: Long,
    crossinline action: suspend (value: T) -> Unit,
) {
    lifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        delay(time)
        collect(action)
    }
}

fun LifecycleOwner.addRepeatingJob(
    state: Lifecycle.State,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit,
) = lifecycleScope.launch(coroutineContext) {
    lifecycle.repeatOnLifecycle(state, block)
}