package com.gsamsonas.mobiliujuprogramavimas.fragments

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gsamsonas.mobiliujuprogramavimas.R
import com.gsamsonas.mobiliujuprogramavimas.core.BATTERY_NOTIFICATION_CHANNEL
import com.gsamsonas.mobiliujuprogramavimas.databinding.FragmentBatteryNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

private const val LOW_BATTERY = 15

@AndroidEntryPoint
class BatteryNotificationFragment : Fragment() {

    private lateinit var binding: FragmentBatteryNotificationBinding

    private lateinit var lowBatteryNotification: Notification

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent?.getIntExtra("level", 100) ?: return
            if (level <= LOW_BATTERY) {
                val service = requireActivity().getSystemService(Activity.NOTIFICATION_SERVICE) as? NotificationManager
                service?.notify(level, lowBatteryNotification)
                requireActivity().unregisterReceiver(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lowBatteryNotification = Notification.Builder(requireContext(), "Battery")
            .setChannelId(BATTERY_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Your battery is low")
            .setContentText("You should charge your phone before the battery runs out")
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBatteryNotificationBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.switchBatteryNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) registerReceiver() else unregisterReceiver()
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver()
    }

    override fun onResume() {
        super.onResume()
        if (binding.switchBatteryNotifications.isChecked) {
            registerReceiver()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver()
    }

    private fun registerReceiver() {
        try {
            requireActivity().registerReceiver(
                broadcastReceiver,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
        } catch (e: Exception) {}
    }

    private fun unregisterReceiver() {
        try {
            requireActivity().unregisterReceiver(broadcastReceiver)
        } catch (e: Exception) {}
    }
}