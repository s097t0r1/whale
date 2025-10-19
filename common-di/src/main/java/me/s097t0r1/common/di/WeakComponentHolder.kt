package me.s097t0r1.common.di

import org.jetbrains.annotations.TestOnly
import java.lang.ref.WeakReference

abstract class WeakComponentHolder<T : DIComponent> : DIComponentHolder<T>() {

    private var INSTANCE: WeakReference<T> = WeakReference(null)

    override fun get(): T {
        return if (INSTANCE.get() == null) {
            INSTANCE = WeakReference(build())
            requireNotNull(INSTANCE.get()) { this::class.simpleName + " component is null" }
        } else {
            requireNotNull(INSTANCE.get()) { this::class.simpleName + " component is null" }
        }
    }

    @TestOnly
    override fun set(component: T) {
        INSTANCE = WeakReference(component)
    }
}