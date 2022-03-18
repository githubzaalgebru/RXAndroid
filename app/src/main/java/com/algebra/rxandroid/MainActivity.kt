package com.algebra.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity( ) {

    val observer = getProgrammingLanguagesObserver( )
    val observerable = getProgrammingLanguagesObserverable( )

    override fun onCreate( savedInstanceState : Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        observerable
            .subscribeOn( Schedulers.io( ) )
            .observeOn( AndroidSchedulers.mainThread( ) )
            .subscribe( observer )

        bPrvi.setOnClickListener {
            DialogExample( ).show( supportFragmentManager, "DE" )
        }

    }
}