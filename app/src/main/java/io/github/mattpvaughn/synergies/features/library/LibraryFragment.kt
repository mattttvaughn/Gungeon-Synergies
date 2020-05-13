package io.github.mattpvaughn.synergies.features.library

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import io.github.mattpvaughn.synergies.application.MainActivity
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.databinding.FragmentLibraryBinding
import io.github.mattpvaughn.synergies.navigation.Navigator
import javax.inject.Inject

class LibraryFragment : Fragment() {

    companion object {
        fun newInstance() = LibraryFragment()
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var moshi: Moshi

    private val adapter = SynergyAdapter(object : ModelClick {
        override fun onClick(synergy: Synergy) {
            openModelDetails(synergy)
        }
    })

    override fun onAttach(context: Context) {
        (activity as MainActivity).activityComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLibraryBinding.inflate(inflater, container, false)

        val json = context!!.assets.open("synergies.json").bufferedReader().use{ it.readText()}
        val viewModelFactory = LibraryViewModelFactory(moshi, json)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(LibraryViewModel::class.java)

        binding.libraryList.adapter = adapter

        viewModel.synergies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    viewModel.query(s.toString())
                }
            }
        })


        return binding.root
    }

    private fun openModelDetails(synergy: Synergy) {

    }

    interface ModelClick {
        fun onClick(synergy: Synergy)
    }
}
