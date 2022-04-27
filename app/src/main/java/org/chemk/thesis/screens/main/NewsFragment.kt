package org.chemk.thesis.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.chemk.thesis.R
import org.chemk.thesis.databinding.FragmentNewsBinding
import org.chemk.thesis.screens.news.CustomRecyclerAdapter
import org.chemk.thesis.screens.news.News
import org.jsoup.Jsoup
import java.io.IOException
import kotlinx.coroutines.*

class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        fillList(4)
        return binding.root
    }

    // Получение данных с сайта и отправка их на адаптер для отрисовки
    private fun fillList(count: Int) {

        lateinit var titleDoc: String
        lateinit var bodyDoc: String
        lateinit var dateDoc: String
        var linkImageDoc: String?
        lateinit var moreDoc: String

        val data = mutableListOf<News>()

        Thread(Runnable {

            try {

                val document = Jsoup.connect("http://www.chemk.org/index.php/sobytiya").get()
                var elements = document.select("div[class=items-leading clearfix]")

                takeFirstNews(data)
                takeNews(data, count)

            } catch (e: IOException) {
                Log.d("ThreadException", "filllist fun")
            }
            activity?.runOnUiThread {
                binding.newsRV.layoutManager = LinearLayoutManager(activity)
                binding.newsRV.adapter = CustomRecyclerAdapter(data)
                binding.newsRV.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }).start()
    }

    // Получение первой новости со страницы
    private fun takeFirstNews(data: MutableList<News>) {

        val document = Jsoup.connect("http://www.chemk.org/index.php/sobytiya").get()
        var elements = document.select("div[class=items-leading clearfix]")

        val titleDoc =
            elements.select("div[class=page-header]")
                .select("a")
                .text()

        val bodyDoc = elements.select("p").text()

        val dateDoc = elements.select("dd[class=published]").text()

        val linkImageDoc = elements.select("img").attr("src") ?: null

        val moreDoc = "http://www.chemk.org" + elements.select("div[class=page-header]")
            .select("a")
            .attr("href")

        data.add(0, News(titleDoc, bodyDoc, dateDoc, linkImageDoc, moreDoc))
        Log.d("PicassoDownload: ", "$linkImageDoc")
        Log.d("MoreInfo: ", "$moreDoc")

    }

    // Получение остальных новостей со страницы
    private fun takeNews(data: MutableList<News>, count: Int, ) {

        val document = Jsoup.connect("http://www.chemk.org/index.php/sobytiya").get()

        for (i in 0..count) {
            var elements = document.select("div[class=span12]")
            var titleDoc =
                elements.select("div[class=page-header]")
                    .select("a")
                    .eq(i)
                    .text()

            var bodyDoc =
                if (document.select("div[class=items-row cols-1 row-$i row-fluid clearfix]") ==
                    document.select("div[class=items-row cols-1 row-$i row-fluid clearfix]")
                ) {
                    document
                        .select("div[class=items-row cols-1 row-$i row-fluid clearfix]")
                        .select("p")
                        .text()
                } else {
                    elements.select("p")
                        .eq(i)
                        .text()
                }

            var dateDoc =
                elements.select("dd[class=published]")
                    .eq(i)
                    .text()

            var linkImageDoc =
                if (document.select("div[class=items-row cols-1 row-$i row-fluid clearfix]") ==
                    document.select("div[class=items-row cols-1 row-$i row-fluid clearfix]")
                ) {
                    document
                        .select("div[class=items-row cols-1 row-$i row-fluid clearfix]")
                        .select("img")
                        .attr("src") ?: null
                } else {
                    elements.select("img")
                        .eq(i)
                        .attr("src")
                }

            var moreDoc = "http://www.chemk.org" + elements.select("div[class=page-header]")
                .select("a")
                .eq(i)
                .attr("href")

            data.add(i + 1, News(titleDoc, bodyDoc, dateDoc, linkImageDoc, moreDoc))
            Log.d("PicassoDownload: ", "$linkImageDoc")
            Log.d("MoreInfo: ", "$moreDoc")

        }
    }
}