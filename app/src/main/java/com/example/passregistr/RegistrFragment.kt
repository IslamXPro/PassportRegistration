package com.example.passregistr

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.passregistr.Adapters.RecycleAdapter
import com.example.passregistr.Database.DataBase
import com.example.passregistr.Entity.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog_rv.*
import kotlinx.android.synthetic.main.dialog_rv.view.*
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_registr.*
import kotlinx.android.synthetic.main.fragment_registr.view.*
import kotlinx.android.synthetic.main.list_rv.view.*
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RegistrFragment : Fragment() {

    lateinit var root: View
    var image: String = ""
    lateinit var dataBase: DataBase
    lateinit var handler: Handler
    lateinit var bundle: Bundle

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_registr, container, false)
        handler = Handler()
        bundle = Bundle()
        dataBase = context?.let { DataBase.getInstance(it) }!!

        root.rg_back_btn.setOnClickListener {
            findNavController().popBackStack()
        }

        root.edt_img_3x4.setOnClickListener {
            getImageCount.launch("image/*")
        }
        val rand = Random()

        val str = "ABCDEFGHIJKLMNOPQRSTUVXYZ"
        var matn = ""
        for (i in 0..1) {
            val randomNumber = rand.nextInt(9)
            matn += str[randomNumber]
        }
        for (i in 0..6) {
            val randomNumber = rand.nextInt(9)
            matn += randomNumber
        }

        val id = matn

        root.rg_save_btn.setOnClickListener {

            val user = User()
            user.userPassportId = id
            user.userName = root.edt_user_name.text.toString().trim()
            user.userSName = root.edt_user_sname.text.toString().trim()
            user.userOName = root.edt_user_oname.text.toString().trim()
            user.userViloyat = when (root.spinner_valley.selectedItemPosition) {
                0 -> "Viloyat"
                1 -> "Andijon"
                2 -> "Buxoro"
                3 -> "Jizzax"
                4 -> "Qoraqalpog'iston"
                5 -> "Qashqadaryo"
                6 -> "Navoiy"
                7 -> "Namangan"
                9 -> "Samarqand"
                10 -> "Surxandaryo"
                11 -> "Sirdaryo"
                12 -> "Toshkent"
                13 -> "Farg'ona"
                14 -> "Xorazm"
                else -> {
                    "Viloyat"
                }
            }
            user.userCity = root.edt_user_city_name.text.toString().trim()
            user.userAddress = root.edt_user_address.text.toString().trim()
            user.userDataStart = root.edt_user_passport_data.text.toString().trim()
            user.userDataEnd = root.edt_user_passport_muddati.text.toString().trim()
            user.userJinsi = when (root.spinner_jinsi.selectedItemPosition) {
                0 -> "Jinsi"
                1 -> "Erkak"
                2 -> "Ayol"
                else -> "Jinsi"
            }
            user.userImage = image

            if (user.userName != "" && user.userName != "" && user.userOName != "" && user.userViloyat != "Viloyat" && user.userCity != "" && user.userAddress != "" && user.userDataStart != "" && user.userDataEnd != "" && user.userJinsi != "Jinsi" && user.userImage != "") {

                val alertDialog = AlertDialog.Builder(context, R.style.SheetDialog).create()
                val view = layoutInflater.inflate(R.layout.dialog_rv, null, false)
                alertDialog.setView(view)
                view.dialog_txt_yes.setOnClickListener {
                    alertDialog.dismiss()
                    root.dialog_anim.visibility = View.VISIBLE
                    handler.postDelayed({
                        root.dialog_anim.visibility = View.GONE
                    }, 4000)
                    handler.postDelayed({
                        Snackbar.make(root, "Succesfully saved", Snackbar.LENGTH_LONG).show()
                    }, 4000)
                    dataBase.userDao().addUser(user)
                    handler.postDelayed({
                        findNavController().navigate(R.id.homeFragment)
                    }, 4050)
                }
                view.dialog_txt_no.setOnClickListener {
                    alertDialog.cancel()
                }
                alertDialog.show()
                bundle.putString("username", user.userName)
                bundle.putString("usersname", user.userSName)
                bundle.putString("useroname", user.userOName)

            } else {
                Snackbar.make(root, "Empty info!", Snackbar.LENGTH_LONG).show()
            }
        }
        return root
    }

    @SuppressLint("SimpleDateFormat")
    private val getImageCount =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
            uri ?: return@registerForActivityResult
            root.edt_img_3x4.setImageURI(uri)
            val inputStream = activity?.contentResolver?.openInputStream(uri)
            val simpleDateFormat = SimpleDateFormat("yyyy|MM|dd_HH:mm:ss").format(Date())
            val file = File(activity?.filesDir, "${simpleDateFormat}rasm.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            image = file.absolutePath
        }
}