package com.znuri.mapro.services.Fragment

class FragmentNavigation {
    interface View {
        fun attachPresenter(presenter:FragmentNavigation.Presenter)
    }

    interface Presenter {
        fun addFragment(fragment: BaseFragment)
    }
}