package info.devexchanges.carousellayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.devexchanges.carousellayout.model.HeroModel;

public class MainActivity extends AppCompatActivity {
    public CarouselPagerAdapter adapter;
    public ViewPager pager;
    /**
     * You shouldn't define first page = 0.
     * Let define firstpage = 'number viewpager size' to make endless carousel
     */
    //public final static int FIRST_PAGE = COUNT * LOOPS / 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.myviewpager);

        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 4) * 2);
        pager.setPageMargin(-pageMargin);

        adapter = new CarouselPagerAdapter(this, getSupportFragmentManager(), listFake());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(adapter.getFirsItemPosition());
        pager.setOffscreenPageLimit(3);
    }

    private List<HeroModel> listFake() {
        return new ArrayList<HeroModel>(){{
            add(new HeroModel("finn",  13, R.drawable.cat_asustado));
            add(new HeroModel("jake",  44, R.drawable.cat_espera));
            add(new HeroModel("marceline",  16, R.drawable.cat_guinio));
            add(new HeroModel("Rey de hielo",  70, R.drawable.cat_garrr));
            add(new HeroModel("Princesa Flama",  17, R.drawable.cat_lengua_afuera));
        }};
    }
}
