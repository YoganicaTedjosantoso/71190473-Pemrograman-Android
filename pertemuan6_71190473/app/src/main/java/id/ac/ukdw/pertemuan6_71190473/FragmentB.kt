package id.ac.ukdw.pertemuan6_71190473

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view: View = inflater.inflate(R.layout.fragment_b, container,
        false)
        val btnB = view.findViewById<Button>(R.id.btnFragmentB)
        btnB.setOnClickListener {
            val intent = Intent(activity, ActivitykeTiga::class.java )
            activity?.startActivity(intent)
        }
        return view
    }
}