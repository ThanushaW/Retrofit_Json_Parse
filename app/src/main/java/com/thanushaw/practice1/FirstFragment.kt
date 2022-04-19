package com.thanushaw.practice1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.google.android.material.snackbar.Snackbar
import com.thanushaw.practice1.api.RetrofitAPI
import com.thanushaw.practice1.api.User
import com.thanushaw.practice1.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    val retrofitAPI = RetrofitAPI.create()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
//            retrofitAPI.getUser().enqueue( object : Callback<User> {
//                override fun onResponse(call: Call<User>?, response: Response<User>?) {
//                   if (response?.body() != null) {
//                       val body = response.body()
//                       if (body != null) {
//                           //binding.textViewSecond.text = "Hi "+body.name
//                           setFragmentResult("requestKey", bundleOf("data" to body))
//                       }
//                    }
//                }
//
//                override fun onFailure(call: Call<User>?, t: Throwable?) {
//                    binding.textViewSecond.text ="Data Retrieve failed !"
//                }
//            })
            val userID = binding.editText.text
            //binding.textViewSecond.text = "Hi "+userID.toString()
            setFragmentResult("requestKey", bundleOf("data" to userID))

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}