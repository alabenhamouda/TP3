package com.example.tp3_p1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val IS_DIGITAL = "digitalOK"
class FragmentClock : Fragment() {
    private var digitalClock = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            digitalClock = it.getBoolean(IS_DIGITAL);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (digitalClock) {
            return inflater.inflate(R.layout.frag_digital, container, false);
        } else {
            return inflater.inflate(R.layout.frag_numeric, container, false);
        }
    }
}