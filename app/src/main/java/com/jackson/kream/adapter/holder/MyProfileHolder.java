package com.jackson.kream.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jackson.kream.R;

public class MyProfileHolder extends RecyclerView.ViewHolder {

    ImageView ivProfile;
    TextView tvNickNm;
    TextView tvProfileEdit;

    public MyProfileHolder(@NonNull View itemView) {
        super(itemView);
        ivProfile = itemView.findViewById(R.id.ivProfile);
        tvNickNm = itemView.findViewById(R.id.tvNickNm);
        tvProfileEdit = itemView.findViewById(R.id.tvProfileEdit);
    }

    public void onBind() {
        Glide.with(ivProfile)
                .load("https://baeomedu.org/images/default/user.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile);

    }
}
