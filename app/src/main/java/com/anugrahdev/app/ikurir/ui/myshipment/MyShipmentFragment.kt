package com.anugrahdev.app.ikurir.ui.myshipment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.anugrahdev.app.ikurir.R
import com.anugrahdev.app.ikurir.adapter.MyPagerAdapter
import com.anugrahdev.app.ikurir.ui.trackwaybill.WaybillViewModel
import com.anugrahdev.app.ikurir.ui.trackwaybill.WaybillViewModelFactory
import com.anugrahdev.app.ikurir.utils.toast
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.myshipment_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MyShipmentFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    val factory: WaybillViewModelFactory by instance<WaybillViewModelFactory>()
    companion object {
        fun newInstance() = MyShipmentFragment()
    }

    private lateinit var viewModel: WaybillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.myshipment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(WaybillViewModel::class.java)

        view_pager.adapter = MyPagerAdapter(this)

        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> { tab.text = "On Process"}
                1 -> { tab.text = "Delivered"}
            }
        }).attach()

        ic_back.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_myShipmentFragment_to_homeFragment, null))


    }


}