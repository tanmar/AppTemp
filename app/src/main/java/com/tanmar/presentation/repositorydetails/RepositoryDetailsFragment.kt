package com.tanmar.presentation.repositorydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tanmar.R
import kotlinx.android.synthetic.main.fragment_repository_details.*


class RepositoryDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedRepository = RepositoryDetailsFragmentArgs.fromBundle(arguments).selectedRepository
        textViewSelectedRepositoryName.text = selectedRepository.name
    }

}
