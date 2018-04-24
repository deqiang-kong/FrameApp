package com.tsbridge.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kong.frameapp.R
import kotlinx.android.synthetic.main.first_fragment.*



class FirstFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initialization()
    }

    private fun initialization() {

        swipeRefresh.setOnRefreshListener {

            Handler().postDelayed({ swipeRefresh.isRefreshing = false }, 5000)
        }



    }




    override fun onResume() {
        super.onResume()

    }


    override fun onDestroyView() {
        super.onDestroyView()

    }
}