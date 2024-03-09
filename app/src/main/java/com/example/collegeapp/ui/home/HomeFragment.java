package com.example.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.collegeapp.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView(view);
        setupMapClickListener(view);

        return view;
    }

    private void setSliderView(View view) {
        String[] imageUrls = {
                "https://firebasestorage.googleapis.com/v0/b/uiet-a2e7c.appspot.com/o/gallery%2F%5BB%4020afa63jpg?alt=media&token=005eb74b-055e-43d5-a4d2-49b4d26e57e2",
                "https://firebasestorage.googleapis.com/v0/b/uiet-a2e7c.appspot.com/o/gallery%2F%5BB%40a01a506jpg?alt=media&token=be09dcdf-ccda-449d-9617-401da93d0316",
                "https://firebasestorage.googleapis.com/v0/b/uiet-a2e7c.appspot.com/o/gallery%2F%5BB%4057baf2jpg?alt=media&token=baf7df37-afba-4cbb-b19c-de40f4b09cba",
                "https://firebasestorage.googleapis.com/v0/b/uiet-a2e7c.appspot.com/o/gallery%2F%5BB%40546142cjpg?alt=media&token=d639d4c7-deef-4f76-964e-10613a71633f",
                "https://firebasestorage.googleapis.com/v0/b/uiet-a2e7c.appspot.com/o/gallery%2F%5BB%4025b3cd2jpg?alt=media&token=9e42cd32-c3a5-4d9d-8c65-4aae52b9c833"
        };

        for (String imageUrl : imageUrls) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());
            sliderView.setImageUrl(imageUrl);
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void setupMapClickListener(View view) {
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=UIET KURUKSHETRA");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}
