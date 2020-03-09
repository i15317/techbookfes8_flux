package jp.digital.future.wearSupporter.flux

interface Action<out T> {
    val data: T
}