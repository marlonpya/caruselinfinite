package info.devexchanges.carousellayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import info.devexchanges.carousellayout.model.HeroModel;

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static int LOOPS = 1000;

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private MainActivity context;
    private FragmentManager fragmentManager;
    private float scale;
    private final List<HeroModel> gaoHeros;

    public CarouselPagerAdapter(MainActivity context, FragmentManager fm, List<HeroModel> paoHeros) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
        this.gaoHeros = paoHeros;
    }

    public int getFirsItemPosition() {
        //COUNT * LOOPS / 2
        return gaoHeros.size() * CarouselPagerAdapter.LOOPS / 2;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == getFirsItemPosition())
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % gaoHeros.size();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ItemFragment.newInstance(context, position, scale, gaoHeros.get(position));
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = gaoHeros.size() * CarouselPagerAdapter.LOOPS;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                CarouselLinearLayout cur = getRootView(position);
                CarouselLinearLayout next = getRootView(position + 1);

                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
                next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }
}