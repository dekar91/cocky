package tech.dekar.cocky.shared.di

interface Logger {
    fun d(tag: String, msg: String): Int
    fun d(tag: String, msg: String, throwable: Throwable): Int
    fun e(tag: String, msg: String, throwable: Throwable): Int
    fun e(tag: String, msg: String): Int
    fun i(tag: String, msg: String): Int
    fun w(tag: String, msg: String): Int
}