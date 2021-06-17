package cn.fanrunqi.materiallogin;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;

public class OverviewActivity extends BottomBarHolderActivity implements FirstFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationPage page1 = new NavigationPage("Material", ContextCompat.getDrawable(this, R.drawable.ic_baseline_handyman_24), FirstFragment.newInstance());
        NavigationPage page2 = new NavigationPage("Module", ContextCompat.getDrawable(this, R.drawable.ic_baseline_fact_check_24), SecondFragment.newInstance());
        NavigationPage page3 = new NavigationPage("Statistics", ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_24dp), ThirdFragment.newInstance());
        NavigationPage page4 = new NavigationPage("Profile", ContextCompat.getDrawable(this, R.drawable.ic_person_black_24dp), FourthFragment.newInstance());

        List<NavigationPage> navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        super.setupBottomBarHolderActivity(navigationPages);
    }

    @Override
    public void onClicked() {
        Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();
    }
}
