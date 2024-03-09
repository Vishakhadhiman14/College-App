package com.example.collegeapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView convoRecycler, otherRecycler;
    private GalleryAdapter convoAdapter, otherAdapter;

    private DatabaseReference galleryRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRecycler = view.findViewById(R.id.convoRecycler);
        otherRecycler = view.findViewById(R.id.otherRecycler);

        galleryRef = FirebaseDatabase.getInstance().getReference().child("gallery");

        setupConvoGallery();
        setupOtherGallery();

        return view;
    }

    private void setupConvoGallery() {
        galleryRef.child("Convocation").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> convoImageList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imageUrl = snapshot.child("gallery").getValue(String.class);
                    if (imageUrl != null) {
                        convoImageList.add(imageUrl);
                    }
                }

                convoAdapter = new GalleryAdapter(getContext(), convoImageList);
                convoRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                convoRecycler.setAdapter(convoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load Convocation images", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupOtherGallery() {
        galleryRef.child("Other Events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> otherImageList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imageUrl = snapshot.child("gallery").getValue(String.class);
                    if (imageUrl != null) {
                        otherImageList.add(imageUrl);
                    }
                }

                otherAdapter = new GalleryAdapter(getContext(), otherImageList);
                otherRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                otherRecycler.setAdapter(otherAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load Other Events images", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
