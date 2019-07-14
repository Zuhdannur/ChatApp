package com.znuri.mapro.services.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open abstract class BaseFragment : Fragment() , FragmentNavigation.View {

    lateinit var nav:FragmentNavigation.Presenter
    lateinit var rootView: View
    override fun attachPresenter(presenter: FragmentNavigation.Presenter) {
        this.nav = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayout(),container,false)
        return rootView
    }
    abstract fun getLayout() : Int

}