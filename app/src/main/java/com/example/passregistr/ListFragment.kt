package com.example.passregistr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.passregistr.Adapters.RecycleAdapter
import com.example.passregistr.Database.DataBase
import com.example.passregistr.Entity.User
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    lateinit var root: View
    lateinit var dataBase: DataBase
    lateinit var recycleAdapter: RecycleAdapter
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_list, container, false)
        bundle = Bundle()

        onResume()
        return root
    }

    override fun onResume() {
        super.onResume()
        dataBase = context?.let { DataBase.getInstance(it) }!!
        val list = ArrayList<User>()
        list.addAll(dataBase.userDao().getAllUser())
        recycleAdapter = RecycleAdapter(list, object : RecycleAdapter.MyClick {
            override fun click(user: User) {
                findNavController().navigate(
                    R.id.infoFragment, bundleOf(
                        "username" to user.userName,
                        "usersname" to user.userSName,
                        "useroname" to user.userOName,
                        "userviloyat" to user.userViloyat,
                        "usercity" to user.userCity,
                        "useraddress" to user.userAddress,
                        "userdatastart" to user.userDataStart,
                        "userdataend" to user.userDataEnd,
                        "userjinsi" to user.userJinsi,
                        "userpassportid" to user.userPassportId,
                        "userimage" to user.userImage

                    )
                )
            }

        })
        root.list_recycle.adapter = recycleAdapter
    }
}