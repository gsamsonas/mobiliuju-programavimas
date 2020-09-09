package com.gsamsonas.mobiliujuprogramavimas.features.externalinput

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.core.EventObserver
import com.gsamsonas.mobiliujuprogramavimas.databinding.ActivityExternalInputBinding
import dagger.hilt.android.AndroidEntryPoint

const val EXTERNAL_INPUT_REQUEST_CODE = 3734
const val EXTERNAL_INPUT_RETURN_CODE = "EXTERNAL_INPUT_RETURN_CODE"

@AndroidEntryPoint
class ExternalInputActivity : AppCompatActivity() {

    private val viewModel: ExternalInputViewModel by viewModels()

    private lateinit var binding: ActivityExternalInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_external_input)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.onSaveInput.observe(this, EventObserver {
            val returnIntent = Intent()
            returnIntent.putExtra(EXTERNAL_INPUT_RETURN_CODE, it)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        })
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ExternalInputActivity::class.java)
        }
    }
}