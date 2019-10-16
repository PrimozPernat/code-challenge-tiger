package com.example.codechallengetiger.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.codechallengetiger.R
import com.example.codechallengetiger.di.viewmodel.ViewModelProviderFactory
import com.example.codechallengetiger.ui.details.DetailsActivity
import com.example.codechallengetiger.util.getViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val recyclerAdapter: JobListAdapter by lazy {
        JobListAdapter {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.JOB_KEY, it)
            startActivity(intent)
        }
    }

    private val viewModel: HomeViewModel by lazy {
        getViewModel<HomeViewModel>(providerFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        observers()
    }

    private fun observers() {
        viewModel.jobLis.observe(this, Observer {
            recyclerAdapter.submitList(it)
        })

        viewModel.error.observe(this, Observer {
            it?.let {
                Snackbar.make(main_container, it, Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpRecyclerView() {
        recycler_view.adapter = recyclerAdapter
    }
}
