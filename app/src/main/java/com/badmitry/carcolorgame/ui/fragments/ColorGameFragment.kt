package com.badmitry.carcolorgame.ui.fragments

import android.graphics.PixelFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.badmitry.carcolorgame.R
import com.badmitry.carcolorgame.databinding.FragmentColorGameBinding
import com.badmitry.carcolorgame.mvp.presenter.ColorGamePresenter
import com.badmitry.carcolorgame.mvp.view.IColorGameView
import com.badmitry.carcolorgame.ui.App
import com.badmitry.carcolorgame.ui.BackBtnListener
import com.badmitry.carcolorgame.ui.GameView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ColorGameFragment: MvpAppCompatFragment(), IColorGameView, BackBtnListener {

    private val CODE_VOICE = "codeVoice"

    companion object {
        fun newInstance(codeVoice: Int) = ColorGameFragment().apply{
            arguments = Bundle().apply {
                putInt(CODE_VOICE, codeVoice)
            }
        }
    }

    private val presenter: ColorGamePresenter by moxyPresenter {
        val codeVoice = arguments?.getInt(CODE_VOICE)
        ColorGamePresenter(codeVoice).apply { App.component.inject(this) }
    }

    private var binding: FragmentColorGameBinding? = null
    private var stopSound = false
    var gameView: GameView? = null
    private var mp: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_color_game, container, false)
        return binding?.root
    }

    override fun startGame() {
        gameView = activity?.applicationContext?.let { GameView(it, presenter) }
        gameView?.setZOrderOnTop(true);
        gameView?.getHolder()?.setFormat(PixelFormat.TRANSPARENT);
        binding?.let {
            it.gameLayout.addView(gameView)
        }
    }

    override fun sound(int: Int) {
        if (!stopSound || !(mp?.isPlaying?:false)){
            activity?.applicationContext?.let {
                stopSound = true
                var soundId = 0
                if (presenter.codeVoice == 0) {
                    when (int) {
                        0 -> soundId = R.raw.pblack
                        1 -> soundId = R.raw.pblue
                        2 -> soundId = R.raw.pbrown
                        3 -> soundId = R.raw.pgreen
                        4 -> soundId = R.raw.pgrey
                        5 -> soundId = R.raw.plblue
                        6 -> soundId = R.raw.porange
                        7 -> soundId = R.raw.ppink
                        8 -> soundId = R.raw.ppurple
                        9 -> soundId = R.raw.pred
                        10 -> soundId = R.raw.pwhite
                        11 -> soundId = R.raw.pyellow
                        else -> {
                            soundId = R.raw.pblack
                        }
                    }
                } else {
                    when (int) {
                        0 -> soundId = R.raw.sblack
                        1 -> soundId = R.raw.sblue
                        2 -> soundId = R.raw.sbrown
                        3 -> soundId = R.raw.sgreen
                        4 -> soundId = R.raw.sgrey
                        5 -> soundId = R.raw.slblue
                        6 -> soundId = R.raw.sorange
                        7 -> soundId = R.raw.spink
                        8 -> soundId = R.raw.spurple
                        9 -> soundId = R.raw.sred
                        10 -> soundId = R.raw.swhite
                        11 -> soundId = R.raw.syellow
                        else -> {
                            soundId = R.raw.sblack
                        }
                    }
                }

                mp = MediaPlayer.create(it, soundId)
                mp?.setOnCompletionListener {
                    stopSound = false
                }
                mp?.start()
            }
        }
    }

    override fun createBlob(colorInt: Int, xCord: Float, yCord: Float) {
        gameView?.createBlob(colorInt, xCord, yCord)
    }

    override fun onResume() {
        super.onResume()
        gameView?.onResumeSurfaceView()
    }

    override fun onPause() {
        super.onPause()
        gameView?.onPauseSurfaceView()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}