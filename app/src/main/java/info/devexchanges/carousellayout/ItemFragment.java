package info.devexchanges.carousellayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import info.devexchanges.carousellayout.model.HeroModel;

public class ItemFragment extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String HERO = "hero";
    private static final String DRAWABLE_RESOURE = "resource";

    private int screenWidth;
    private int screenHeight;

    public static Fragment newInstance(MainActivity context, int pos, float scale, HeroModel poHeroModel) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);
        b.putSerializable(ItemFragment.HERO, poHeroModel);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int liPosition = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);
        final HeroModel loHeroModel = (HeroModel) this.getArguments().getSerializable(ItemFragment.HERO);
        assert loHeroModel != null;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth / 2, screenHeight / 2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_image, container, false);

        TextView textViewName = (TextView) linearLayout.findViewById(R.id.txtName);
        TextView textViewAge = (TextView) linearLayout.findViewById(R.id.txtAge);
        TextView textViewPosition = (TextView) linearLayout.findViewById(R.id.txtPosition);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);

        textViewName.setText(loHeroModel.getName());
        textViewAge.setText(String.valueOf(loHeroModel.getAge()));
        textViewPosition.setText("Position " + liPosition);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(loHeroModel.getRscCat());


        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}
