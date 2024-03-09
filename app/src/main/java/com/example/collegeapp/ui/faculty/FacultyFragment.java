package com.example.collegeapp.ui.faculty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.example.collegeapp.ui.faculty.TeacherAdapter;
import com.example.collegeapp.ui.faculty.TeacherData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {
    private RecyclerView cseDepartment, eceDepartment, mechanicalDepartment, biotechDepartment;
    private LinearLayout csNoData, mechNoData, eceNoData, biotechNoData;
    private List<TeacherData> list1, list2, list3, list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        csNoData = view.findViewById(R.id.csNoData);
        mechNoData = view.findViewById(R.id.mechNoData);
        eceNoData = view.findViewById(R.id.eceNoData);
        biotechNoData = view.findViewById(R.id.biotechNoData);

        cseDepartment = view.findViewById(R.id.cseDepartment);
        eceDepartment = view.findViewById(R.id.eceDepartment);
        mechanicalDepartment = view.findViewById(R.id.mechanicalDepartment);
        biotechDepartment = view.findViewById(R.id.biotechDepartment);

        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        setupDepartment(cseDepartment, "CSE", csNoData);
        setupDepartment(eceDepartment, "ECE", eceNoData);
        setupDepartment(mechanicalDepartment, "MEC", mechNoData);
        setupDepartment(biotechDepartment, "BioTech", biotechNoData);

        return view;
    }

    private void setupDepartment(final RecyclerView departmentRecyclerView, String departmentName, final LinearLayout noDataLayout) {
        DatabaseReference departmentRef = reference.child(departmentName);
        departmentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<TeacherData> teacherList = new ArrayList<>();
                if (!snapshot.exists()) {
                    noDataLayout.setVisibility(View.VISIBLE);
                    departmentRecyclerView.setVisibility(View.GONE);
                } else {
                    noDataLayout.setVisibility(View.GONE);
                    departmentRecyclerView.setVisibility(View.VISIBLE);
                }
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    TeacherData data = snapshot1.getValue(TeacherData.class);
                    teacherList.add(data);
                }
                departmentRecyclerView.setHasFixedSize(true);
                departmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new TeacherAdapter(teacherList, getContext());
                departmentRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
