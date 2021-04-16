package ruzanna.game.refrofitnewsapi

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArticlesAdapter()
        newsRecyclerView.adapter = adapter
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        getstart("Korlin", adapter)

        search_bar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getstart(query!!, adapter)
                search_bar.isIconified = true
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getstart(q: String, adapter: ArticlesAdapter) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Waiting for data loading.")
        progressDialog.setMessage("Loading...")

        val articleAPI = ApiService.retrofit.create(ArticleApi::class.java)
        val call: Call<MainResponse> = articleAPI.getArticles(q)

        progressDialog.show()

        call.enqueue(object : Callback<MainResponse> {

            override fun onResponse(
                call: Call<MainResponse>,
                response: Response<MainResponse>
            ) {
                newsRecyclerView.post {
                    adapter.articlesList = response.body()!!.articles
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
            }

        })
    }


}

