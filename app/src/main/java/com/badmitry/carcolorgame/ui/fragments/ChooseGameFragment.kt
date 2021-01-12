package com.badmitry.carcolorgame.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.badmitry.carcolorgame.R
import com.badmitry.carcolorgame.databinding.FragmentChooseGameBinding
import com.badmitry.carcolorgame.mvp.presenter.ChooseGamePresenter
import com.badmitry.carcolorgame.mvp.view.IChooseGameView
import com.badmitry.carcolorgame.ui.App
import com.badmitry.carcolorgame.ui.BackBtnListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ChooseGameFragment : MvpAppCompatFragment(), IChooseGameView, BackBtnListener {
    companion object {
        fun newInstance() = ChooseGameFragment()
    }

    private val presenter: ChooseGamePresenter by moxyPresenter {
        ChooseGamePresenter().apply { App.component.inject(this) }
    }

    private var binding: FragmentChooseGameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_choose_game, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let{
            it.startColorGame.setOnClickListener {
                presenter.startColorGame()
            }
            it.settings.setOnClickListener{
                presenter.openSettings()
            }
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}