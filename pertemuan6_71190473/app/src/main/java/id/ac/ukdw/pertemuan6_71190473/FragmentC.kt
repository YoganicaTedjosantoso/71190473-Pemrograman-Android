package id.ac.ukdw.pertemuan6_71190473

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentC : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_c, container,
            false
        )
        val btnC = view.findViewById<Button>(R.id.btnFragmentC)
        btnC.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java )
            activity?.startActivity(intent)
        }
        return view
    }
}