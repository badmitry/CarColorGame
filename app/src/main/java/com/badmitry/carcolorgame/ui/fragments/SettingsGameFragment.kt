package com.badmitry.carcolorgame.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.badmitry.carcolorgame.R
import com.badmitry.carcolorgame.databinding.FragmentChooseGameBinding
import com.badmitry.carcolorgame.databinding.FragmentSettingsGameBinding
import com.badmitry.carcolorgame.mvp.presenter.SettingsGamePresenter
import com.badmitry.carcolorgame.mvp.view.ISettingsGameView
import com.badmitry.carcolorgame.ui.App
import com.badmitry.carcolorgame.ui.BackBtnListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SettingsGameFragment : MvpAppCompatFragment(), ISettingsGameView, BackBtnListener {
    companion object {
        fun newInstance() = SettingsGameFragment()
    }

    private val presenter: SettingsGamePresenter by moxyPresenter {
        SettingsGamePresenter().apply { App.component.inject(this) }
    }

    private var binding: FragmentSettingsGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings_game, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let{
            it.rbRougherVoices.setOnClickListener{
                presenter.saveChoseVoice(0)
            }
            it.rbSofterVoices.setOnClickListener{
                presenter.saveChoseVoice(1)
            }
        }
    }

    override fun setChoseVoice(code: Int) {
        binding?.let {
            when (code) {
                0 -> it.rbRougherVoices.isChecked = true
                1 -> it.rbSofterVoices.isChecked = true
            }
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}