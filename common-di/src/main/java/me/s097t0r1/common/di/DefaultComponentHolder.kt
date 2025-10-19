package me.s097t0r1.common.di

import org.jetbrains.annotations.TestOnly

abstract class DefaultComponentHolder<T : DIComponent> : DIComponentHolder<T>() {

    @Volatile
    private var INSTANCE: T? = null

    override fun get(): T {
        if (INSTANCE == null) {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = build()
                }
            }
        }

        return requireNotNull(INSTANCE) {  this::class.java.simpleName + " instance is null" }
    }

    @TestOnly
    override fun set(component: T) {
        INSTANCE = component
    }
}