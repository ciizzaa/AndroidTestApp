package com.example.androidtestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtestapp.CustomItem
import com.example.androidtestapp.R
import com.example.androidtestapp.RcyclerAdapter
import com.example.androidtestapp.WorkSpaceActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val layout = inflater.inflate(R.layout.fragment_contacts, container, false)
        val items = ArrayList<CustomItem>()

        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))
        items.add(CustomItem("Contact in progress", R.drawable.androidgreen))

        val recyclerView = layout.findViewById<RecyclerView>(R.id.recyclerDataView)
        val adapter = RcyclerAdapter(items, requireActivity().applicationContext)

        recyclerView.layoutManager = GridLayoutManager(requireActivity().applicationContext, 1)
        recyclerView.adapter = adapter

        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ContactsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}