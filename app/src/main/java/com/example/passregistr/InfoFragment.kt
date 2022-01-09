package com.example.passregistr

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.passregistr.Adapters.RecycleAdapter
import com.example.passregistr.Database.DataBase
import com.example.passregistr.Entity.User
import kotlinx.android.synthetic.main.fragment_info.view.*


class InfoFragment : Fragment() {

    lateinit var root: View
    lateinit var dataBase: DataBase
    lateinit var recycleAdapter: RecycleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_info, container, false)
        onResume()
        root.info_back_btn.setOnClickListener {
            findNavController().popBackStack()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        val username = arguments?.getString("username", "key1")
        val usersname = arguments?.getString("usersname", "key2")
        val useroname = arguments?.getString("useroname", "key3")
        val userviloyat = arguments?.getString("userviloyat", "key4")
        val usercity = arguments?.getString("usercity", "key5")
        val useraddress = arguments?.getString("useraddress", "key6")
        val userdatastart = arguments?.getString("userdatastart", "key7")
        val userdataend = arguments?.getString("userdataend", "key8")
        val userjinsi = arguments?.getString("userjinsi", "key9")
        val userpassportid = arguments?.getString("userpassportid", "key10")
        val userimage = arguments?.getString("userimage", "key11")

        root.info_user_name.text = username
        root.info_user_sname.text = usersname
        root.info_user_oname.text = useroname
        root.info_user_viloyat.text = userviloyat
        root.info_user_city.text = usercity
        root.info_user_address.text = useraddress
        root.info_user_datastart.text = userdatastart
        root.info_user_dataend.text = userdataend
        root.info_user_jinsi.text = userjinsi
        root.info_user_idnumber.text = userpassportid
        root.info_user_image.setImageURI(Uri.parse(userimage))

    }
}