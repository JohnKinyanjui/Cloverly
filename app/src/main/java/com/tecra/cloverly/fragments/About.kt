package com.tecra.cloverly.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.tecra.cloverly.R

/**
 * A simple [Fragment] subclass.
 */
class About : Fragment() {
    lateinit var mAdView : AdView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        MobileAds.initialize(this.context) {}
        mAdView = view.findViewById(R.id.ad2)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        return view
    }


}
