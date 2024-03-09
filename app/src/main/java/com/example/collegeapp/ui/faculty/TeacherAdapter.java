package com.example.collegeapp.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;
import com.example.collegeapp.ui.faculty.TeacherData;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private List<TeacherData> teacherList;
    private Context context;

    public TeacherAdapter(List<TeacherData> teacherList, Context context) {
        this.teacherList = teacherList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        TeacherData teacher = teacherList.get(position);
        holder.bind(teacher);
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {
        private ImageView teacherImage;
        private TextView teacherName, teacherEmail, teacherPost;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherImage = itemView.findViewById(R.id.teacherImage);
            teacherName = itemView.findViewById(R.id.teacherName);
            teacherEmail = itemView.findViewById(R.id.teacherEmail);
            teacherPost = itemView.findViewById(R.id.teacherPost);
        }

        public void bind(TeacherData teacher) {
            teacherName.setText(teacher.getName());
            teacherEmail.setText(teacher.getEmail());
            teacherPost.setText(teacher.getPost());

            Glide.with(context)
                    .load(teacher.getImage())
                    .placeholder(R.drawable.profile)
                    .into(teacherImage);
        }
    }
}
