package com.example.firebasefirestore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firebasefirestore.R
import com.example.firebasefirestore.databinding.FragmentPostBinding
import com.example.firebasefirestore.models.MyImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class PostFragment : Fragment() {
    private val binding by lazy { FragmentPostBinding.inflate(layoutInflater) }
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("my_files")

        binding.apply {
            btnPost.setOnClickListener {
                if (imageUrl != "" && edtName.text.toString().isNotBlank()) {
                    firebaseFirestore.collection("images")
                        .add(MyImage(edtName.text.toString(), imageUrl))
                        .addOnSuccessListener {
                            Toast.makeText(context, "saqlandi", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "malumot toldirilmadi", Toast.LENGTH_SHORT).show()
                }
            }
            itemImage.setOnClickListener {
                getImageContent.launch("image/*")
            }
        }
        return binding.root
    }

    var imageUrl = ""
    private var getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->//bu uri papkani ichida manaqa bunaqa keldigon uri

            if (uri != null) {
                val m = System.currentTimeMillis()
                val uploadTask = reference.child(m.toString()).putFile(uri)
                uploadTask.addOnSuccessListener {
                    val downloadUrl =
                        it.metadata?.reference?.downloadUrl//bu uri firebase joylashgan uri si
                    downloadUrl?.addOnSuccessListener { imageUri ->
                        imageUrl = imageUri.toString()
                    }
                }

            }
            binding.itemImage.setImageURI(uri)
        }
}