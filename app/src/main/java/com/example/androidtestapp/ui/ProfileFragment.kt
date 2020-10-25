package com.example.androidtestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.androidtestapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var nameTextView: TextView? = null
    private var emailTextView: TextView? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_profile, container, false)
        nameTextView = layout.findViewById<TextView>(R.id.fullNameTextView)
        emailTextView = layout.findViewById<TextView>(R.id.emailTextView)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference
        showUserInfo()
        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun showUserInfo() {
        var str: String? = null

        var userData = firebaseDatabase?.child("Users")?.child(firebaseAuth?.currentUser!!.uid)
        userData?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(task: DataSnapshot) {


                if (task.exists()) {
                    var name = task.child("firstName").value as String
                    var lastname = task.child("lastName").value as String
                    var email = task.child("email").value as String

                    nameTextView?.text = name+" "+lastname
                    emailTextView?.text = email

                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(activity!!.applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }


        })


    }
}