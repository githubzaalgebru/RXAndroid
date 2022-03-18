package com.algebra.rxandroid

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


fun getProgrammingLanguagesObserverable( ) : Observable< String > {
    return Observable.just( "Android", "Kotlin", "Java", "Javascript", "Spring", "RxKotlin" )
}

fun getProgrammingLanguagesObserver( ) : Observer< String > {
    return object : Observer< String > {
        override fun onSubscribe( d : Disposable ) {
            Log.i( "onSubscribe", d.toString( ) )
        }

        override fun onNext( t : String ) {
            Log.i( "onNext", t )
        }

        override fun onError( e : Throwable ) {
            Log.i( "onError", e.toString( ) )
        }

        override fun onComplete( ) {
            Log.i( "onComplete", "DONE!!!" )
        }

    }
}