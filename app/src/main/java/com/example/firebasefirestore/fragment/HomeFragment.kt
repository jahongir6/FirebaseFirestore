package com.example.firebasefirestore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.firebasefirestore.R
import com.example.firebasefirestore.adapter.UserAdapter
import com.example.firebasefirestore.databinding.FragmentHomeBinding
import com.example.firebasefirestore.models.MyImage
import com.google.firebase.firestore.FirebaseFirestore


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("images")
            .get()
            .addOnCompleteListener {
                val list = ArrayList<MyImage>()
                if (it.isSuccessful) {
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(MyImage::class.java)
                        list.add(user)
                    }
                    userAdapter = UserAdapter(list)
                    binding.rv.adapter = userAdapter
                }
            }


        return binding.root
    }
}