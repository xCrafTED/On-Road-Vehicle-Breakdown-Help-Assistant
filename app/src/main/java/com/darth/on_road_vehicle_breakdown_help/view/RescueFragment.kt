package com.darth.on_road_vehicle_breakdown_help.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.darth.on_road_vehicle_breakdown_help.R
import com.darth.on_road_vehicle_breakdown_help.databinding.FragmentNotificationBinding
import com.darth.on_road_vehicle_breakdown_help.databinding.FragmentRescueBinding
import com.google.firebase.auth.FirebaseAuth


class RescueFragment : Fragment() {

    private var _binding : FragmentRescueBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRescueBinding.inflate(inflater, container, false)

        val dataFromSQL = 1

        if (dataFromSQL == 0) {

            binding.rescueInformationText.visibility = View.VISIBLE
            binding.createRescueRequest.visibility = View.VISIBLE

            binding.currentLocationText.visibility = View.GONE
            binding.mapsFrame.visibility = View.GONE
            binding.addressDirectionText.visibility = View.GONE
            binding.mapsFrame.visibility = View.GONE
            binding.addressDirectionText.visibility = View.GONE
            binding.vehicleLocation.visibility = View.GONE
            binding.vehicleLocationText.visibility = View.GONE
            binding.vehicleProblem.visibility = View.GONE
            binding.buttonUpdate.visibility = View.GONE
            binding.buttonCancel.visibility = View.GONE

            binding.createRescueRequest.setOnClickListener {
                val intent = Intent(requireContext(), MapsActivity::class.java)
                intent.putExtra("key", "create")
                startActivity(intent)
            }



        }else {

            binding.rescueInformationText.visibility = View.GONE
            binding.createRescueRequest.visibility = View.GONE

            val cancelButton = binding.root.findViewById<Button>(R.id.buttonCancel)
            val updateButton = binding.root.findViewById<Button>(R.id.buttonUpdate)



            updateButton.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Update")
                builder.setMessage("Are you sure you want to update the help request?")
                builder.setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(requireContext(), MapsActivity::class.java)
                    intent.putExtra("key", "update")
                    startActivity(intent)
                }
                builder.setNegativeButton("No") { _, _ ->
                    // empty...
                }
                builder.create().show()
            }


            cancelButton.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Delete")
                builder.setMessage("Are you sure you want to delete the help request?")
                builder.setPositiveButton("Yes") { _, _ ->
                    // Delete job
                }
                builder.setNegativeButton("No") { _, _ ->
                    // empty...
                }
                builder.create().show()
            }
        }
        return binding.root

    }



    fun cancelRequest(view: View) {}
    fun updateRequest(view: View) {}




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

