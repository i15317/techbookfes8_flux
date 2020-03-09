package jp.digital.future.wearSupporter.util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class Messenger {
    private val _bus = PublishSubject.create<Message>().toSerialized()

    fun send(message: Message) {
        _bus.onNext(message)
    }

    fun <T : Message> register(messageClazz: Class<out T>): Observable<T> {
        val objectSubject = PublishSubject.create<Any>().toSerialized()
        return _bus
            .ofType(messageClazz)
            .map { message -> message }
    }
}