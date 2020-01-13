package com.example.amanelshark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.amanelshark.R;

public class  SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

//    private TextView slideHeading, slideDescription;
//    private ImageView slide_imageView;


    public SliderAdapter(Context context) {

        this.context = context;
    }

    // img Array
    public int[] image_slide = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four

    };

    // heading Array
    public String[] heading_slide = {
            "Peace of mind",
            "Protection from Unexpected Auto Repairs",
            "24/7 Roadside Assistance",
            "Replacement Vehicle"
    };

    // description Array
    public String[] description_slide = {
            "The primary reason for an extended warranty is to give the customer peace of mind. A fair bit of comfort is derived knowing that should a failure occur after manufacturer’s warranty expire, will be fixed at no additional charge",
            "We can help you save on the cost of repairs by protecting your vehicle with an extended warranty. In most cases, the policy premium will cost you less than the repairs of major breakdowns",
            " When driving in town or traveling across the country. You need to travel with confidence and peace of mind. Aman Asharq RSA will assist you if you get stranded with a flat tire, breakdown, dead battery, or an empty gas tank. You know that our help is just a phone call away. WE GUARANTEE TO YOU MOVING 24 Hours a day, 365 Days a Year",
            "In case of a Road Accident to the fully Insured Vehicle, provided that such an accident is covered under the Motor Insurance Policy terms & conditions, Aman Asharq will provide the Insured with a replacement vehicle from Rent A Car Company only for the duration of the repairs and subject to the maximum number of days"
    };


    @Override
    public int getCount() {

        return heading_slide.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        container.addView(view);

        ImageView slide_imageView = view.findViewById(R.id.imageView1);
        TextView slideHeading = view.findViewById(R.id.tvHeading);
        TextView slideDescription = view.findViewById(R.id.tvDescription);

        slide_imageView.setImageResource(image_slide[position]);
        slideHeading.setText(heading_slide[position]);
        slideDescription.setText(description_slide[position]);

        return view;
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}


