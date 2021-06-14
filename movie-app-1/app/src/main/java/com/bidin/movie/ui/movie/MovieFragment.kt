package com.bidin.movie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bidin.movie.R
import com.bidin.movie.data.DataEntity
import com.bidin.movie.databinding.FragmentMovieBinding
import com.bidin.movie.utils.ShareButtonCallback

class MovieFragment : Fragment(), ShareButtonCallback {

    private lateinit var fragmentMovieBiding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        fragmentMovieBiding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBiding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapter = MovieAdapter(this)
            movieAdapter.setMovies(movies)

            with(fragmentMovieBiding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun onShareClick(data: DataEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan film ini sekarang.")
                    .setText(resources.getString(R.string.share_text, data.title))
                    .startChooser()
        }
    }
}