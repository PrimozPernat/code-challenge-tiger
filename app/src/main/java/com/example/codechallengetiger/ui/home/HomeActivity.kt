package com.example.codechallengetiger.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.codechallengetiger.R
import com.example.codechallengetiger.di.viewmodel.ViewModelProviderFactory
import com.example.codechallengetiger.ui.details.DetailsActivity
import com.example.codechallengetiger.util.getViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

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

    var startTimer = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        startTimer = System.currentTimeMillis()
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        observers()
    }

    private fun observers() {
        viewModel.jobLis.observe(this, Observer {
            recyclerAdapter.submitList(it)
            Log.d("Primoz", "${System.currentTimeMillis() - startTimer}")
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
