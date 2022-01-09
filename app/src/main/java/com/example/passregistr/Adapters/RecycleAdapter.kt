package com.example.passregistr.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passregistr.Entity.User
import com.example.passregistr.databinding.ListRvBinding
import java.lang.StringBuilder
import kotlin.random.Random

class RecycleAdapter(val list: List<User>, var myClick: MyClick) :
    RecyclerView.Adapter<RecycleAdapter.Vh>() {


    inner class Vh(var itemRv: ListRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: User, pos: Int) {
            itemRv.rvUserName.text = "${pos + 1}.${user.userName} ${user.userSName}"
            itemRv.rvPassportId.text = user.userPassportId

            itemRv.root.setOnClickListener {
                myClick.click(user)
            }
        }
    }

    interface MyClick {
        fun click(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ListRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}