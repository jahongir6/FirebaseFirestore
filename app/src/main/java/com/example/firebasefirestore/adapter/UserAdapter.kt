package com.example.firebasefirestore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasefirestore.databinding.ItemRvBinding
import com.example.firebasefirestore.models.MyImage
import com.squareup.picasso.Picasso

class UserAdapter(var list: ArrayList<MyImage> = ArrayList()) :
    RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myImage: MyImage) {
            itemRvBinding.itemEdtName.text=myImage.name
//            Picasso.get().load(myImage.imagePath).into(itemRvBinding.itemImg)  shu qoshsang galereyadan rasm olib kelib qoysan boladi lekin picasso ishlamayapti
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}