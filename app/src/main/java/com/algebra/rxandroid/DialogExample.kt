package com.algebra.rxandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_reactive.*

class DialogExample : DialogFragment( ) {
    private lateinit var disposable: Disposable
    private var result: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val anyObservable = getAnyObservable( )
        disposable = anyObservable
            .subscribeOn( Schedulers.io( ) )
            .observeOn( AndroidSchedulers.mainThread( ) )
            .subscribeBy(
                onNext = {
                    Log.i("onNext", it.toString())
                    result += "$it, \n\n"
                },
                onError = {
                    Log.i("onError", it.toString())
                },
                onComplete = {
                    Log.i("onNext", "DONE!!")
                    tvResult.text = result
                }
            )
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate( R.layout.dialog_reactive, container, false )
    }

    private fun getAnyObservable(): Observable< Any > {
        return listOf( true, 1, 2, "Three", 4.0f, 4.5, "Five", false ).toObservable( )
    }

    override fun onDestroy( ) {
        super.onDestroy( )

        disposable.dispose( )
    }

}