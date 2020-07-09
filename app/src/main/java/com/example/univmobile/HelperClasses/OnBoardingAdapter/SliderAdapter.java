package com.example.univmobile.HelperClasses.OnBoardingAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.univmobile.R;

//digunakan untuk menampilkan gambar dan slide layar utama
public class SliderAdapter extends PagerAdapter {

    Context context; // klik kanan->generator->construc

    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.first,
            R.drawable.second2,
            R.drawable.third,
            R.drawable.fourth
            // untuk menyelaraskan tekan alt+ctrl+L
    };

    int headings[] = {
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title
            // untuk menyelaraskan tekan alt+ctrl+L
    };

    int descriptions[] = {
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc
            // untuk menyelaraskan tekan alt+ctrl+L
    };

    @Override
    public int getCount() {
        return headings.length; // brapa banyak slide yg kita buat // arti heading.length melihat array headings ada brp
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    //ctrl+o pilih instantiateitem //untuk menginilisasi item
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE); //request system ervice desain
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false); //requset arahkan letak slide

        //Hooks
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }


    //ctrl+o pilih destroyItem //untuk menghancurkan item yang tidak ingin kita gunakan
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //wadah menghapus tampilan
        container.removeView((ConstraintLayout)object);
    }

}
