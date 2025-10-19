package me.s097t0r1.common.di

abstract class DIComponentHolder<C : DIComponent> {

    abstract fun get(): C

    abstract fun set(component: C)

    abstract fun build(): C
}