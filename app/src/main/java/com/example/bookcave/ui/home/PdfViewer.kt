package com.example.bookcave.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookcave.R
import com.github.barteksc.pdfviewer.PDFView

class PdfViewer: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.e("AAA", "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        (this as AppCompatActivity).supportActionBar?.title = intent.getStringExtra("title")

        intent.getStringExtra("file_name").let {
            val fileName = if (it.isNullOrEmpty()) "sample.pdf" else "$it.pdf"
            findViewById<PDFView>(R.id.pdfView).fromAsset(fileName)
                .load()
        }

    }



}
