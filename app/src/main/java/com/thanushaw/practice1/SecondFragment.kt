package com.thanushaw.practice1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.google.android.material.snackbar.Snackbar
import com.thanushaw.practice1.api.RetrofitAPI
import com.thanushaw.practice1.api.User
import com.thanushaw.practice1.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get parsed argument
        val userId = arguments?.getInt("data")
        if(userId!=0) {
            //Creating RetrofitAPI instance
            val retrofitAPI = RetrofitAPI.create()
            //Enqueuing getUser method
            retrofitAPI.getUser(userId!!).enqueue( object : Callback<User> {
                //If response successfully received
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val body = response.body()
                    if (body != null) {
                        //Binding each data into each textView
                        binding.textviewName.text = body.name
                        binding.textviewUname.text = body.username
                        binding.textviewEmail.text = body.email
                        binding.textviewAddress.text = body.address.suite+", "+body.address.street+", "+body.address.city+", "+body.address.zipcode+",\n latitude: "+body.address.geo.lat+", longitude: "+body.address.geo.lng
                        binding.textviewPhone.text = body.phone
                        binding.textviewWebsite.text = body.website
                        binding.textviewCompany.text = body.company.name+"\n"+body.company.catchPhrase+"\n"+body.company.bs
                    }
                }
                //if failed
                @SuppressLint("SetTextI18n")
                override fun onFailure(call: Call<User>, t: Throwable) {

                    Snackbar.make(view, R.string.errorMsgConnection, Snackbar.LENGTH_SHORT)
                        .show()
                }
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}