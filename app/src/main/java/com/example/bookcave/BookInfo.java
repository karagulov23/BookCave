package com.example.bookcave;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookcave.db.AppDatabase;
import com.example.bookcave.db.models.Course;
import com.example.bookcave.db.models.Lecture;
import com.example.bookcave.ui.home.PdfViewer;

import java.util.List;

public class BookInfo extends AppCompatActivity {

    ImageView bthumbnail;
    TextView btitle, bcategory, bprice, bauthor;
    RecyclerView lecturesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_book_info);

        bthumbnail = findViewById(R.id.bthumbnail);
        btitle = findViewById(R.id.btitle);
        bcategory = findViewById(R.id.bcategory);
        bprice = findViewById(R.id.bprice);
        bauthor = findViewById(R.id.bauthor);
        lecturesRecyclerView = findViewById(R.id.lecturesRecyclerView);

        Intent intent = getIntent();
        final Integer courseId = intent.getIntExtra("course_id", 0);

        Course course = AppDatabase.getInstance(getApplicationContext()).dbDao().getCourseById(courseId);

        ((AppCompatActivity)this).getSupportActionBar().setTitle(course.getName());

        List<Lecture> lectures = AppDatabase.getInstance(getApplicationContext()).dbDao().getLectures(courseId);

//        bshow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse(preview);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }
//        });

        btitle.setText(course.getName());
        bcategory.setText(course.getDescription());
        bprice.setText(course.getUniversity());
        bauthor.setText(course.getCategory());

        String uri = "@drawable/" + course.getImgName();  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable drawable = getResources().getDrawable(imageResource);
        bthumbnail.setImageDrawable(drawable);


        lecturesRecyclerView.setHasFixedSize(true);
        lecturesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LecturesAdapter adapter = new LecturesAdapter(lectures, this);
        lecturesRecyclerView.setAdapter(adapter);

    }

    private static class LectureViewHolder extends RecyclerView.ViewHolder {
        View mView;
        LinearLayout container;
        TextView row_title;
        TextView row_author;

        LectureViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            container = mView.findViewById(R.id.container);
            row_title = mView.findViewById(R.id.row_title);
            row_author = mView.findViewById(R.id.row_author);
        }
    }

    private class LecturesAdapter extends RecyclerView.Adapter<LectureViewHolder> {

        private List<Lecture> lectures;
        private Context context;

        LecturesAdapter(List<Lecture> items, Context context) {
            lectures = items;
            this.context = context;
        }

        @NonNull
        @Override
        public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_holder_lecture, parent, false);
            return new LectureViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LectureViewHolder viewHolder, int position) {
            final Lecture lecture = lectures.get(position);

            viewHolder.row_title.setText(lecture.getName());
            viewHolder.row_author.setText(lecture.getDescription());

            viewHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, PdfViewer.class);
                    i.putExtra("title", lecture.getName());
                    i.putExtra("file_name", lecture.getFileName());

                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return lectures.size();
        }
    }
}
