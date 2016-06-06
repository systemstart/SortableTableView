package de.codecrafters.tableviewexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableviewexample.data.Car;
import de.codecrafters.tableviewexample.data.CarProducer;

public class MainActivity extends AppCompatActivity {
    public static final ArrayList<String> menuItems = new ArrayList<String>() {
        {
            add("One");
            add("Two");
            add("Three");
        }
    };

    private List<Car> createAndGiveCarList(String prefix) {
        final CarProducer audi = new CarProducer(R.mipmap.audi, prefix + " Audi");
        final Car audiA1 = new Car(audi, prefix + " A1", 150, 25000);
        final Car audiA3 = new Car(audi, prefix + " A3", 120, 35000);
        final Car audiA4 = new Car(audi, prefix + " A4", 210, 42000);
        final Car audiA5 = new Car(audi, prefix + " S5", 333, 60000);
        final Car audiA6 = new Car(audi, prefix + " A6", 250, 55000);
        final Car audiA7 = new Car(audi, prefix + " A7", 420, 87000);
        final Car audiA8 = new Car(audi, prefix + " A8", 320, 110000);

        final CarProducer bmw = new CarProducer(R.mipmap.bmw, prefix + " BMW");
        final Car bmw1 = new Car(bmw, prefix + " 1er", 170, 25000);
        final Car bmw3 = new Car(bmw, prefix + " 3er", 230, 42000);
        final Car bmwX3 = new Car(bmw, prefix + " X3", 230, 45000);
        final Car bmw4 = new Car(bmw, prefix + " 4er", 250, 39000);
        final Car bmwM4 = new Car(bmw, prefix + " M4", 350, 60000);
        final Car bmw5 = new Car(bmw, prefix + " 5er", 230, 46000);

        final CarProducer porsche = new CarProducer(R.mipmap.porsche, prefix + " Porsche");
        final Car porsche911 = new Car(porsche, prefix + " 911", 280, 45000);
        final Car porscheCayman = new Car(porsche, prefix + " Cayman", 330, 52000);
        final Car porscheCaymanGT4 = new Car(porsche, prefix + " Cayman GT4", 385, 86000);

        final List<Car> cars = new ArrayList<>();
        cars.add(audiA3);
        cars.add(audiA1);
        cars.add(porscheCayman);
        cars.add(audiA7);
        cars.add(audiA8);
        cars.add(audiA4);
        cars.add(bmwX3);
        cars.add(porsche911);
        cars.add(bmw1);
        cars.add(audiA6);
        cars.add(audiA5);
        cars.add(bmwM4);
        cars.add(bmw5);
        cars.add(porscheCaymanGT4);
        cars.add(bmw3);
        cars.add(bmw4);

        return cars;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        TabLayout tabs = (TabLayout) findViewById(R.id.navTabs);
        final ViewPager pager = (ViewPager) findViewById(R.id.navPager);
        NavPagerAdapter adapter = new NavPagerAdapter(getSupportFragmentManager(), getLayoutInflater());

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

    }

    @SuppressLint("ValidFragment")
    public class SomeFragment extends Fragment {

        private String prefix = "Some";

        public SomeFragment() {
        }

        ;

        public SomeFragment(String prefix) {
            this.prefix = prefix;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.some_fragment, container, false);
            final SortableCarTableView carTableView = (SortableCarTableView) view.findViewById(R.id.tableView);
            carTableView.setDataAdapter(new CarTableDataAdapter(getContext(), createAndGiveCarList(this.prefix)));
            carTableView.addDataClickListener(new CarClickListener());

            return view;
        }
    }

    class NavPagerAdapter extends FragmentPagerAdapter {
        private final LayoutInflater inflater;

        public NavPagerAdapter(FragmentManager fm, LayoutInflater inflater) {
            super(fm);
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public Fragment getItem(int position) {
            return new SomeFragment(menuItems.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return menuItems.get(position);
        }
    }

    private class CarClickListener implements TableDataClickListener<Car> {

        @Override
        public void onDataClicked(final int rowIndex, final Car clickedData) {
            final String carString = clickedData.getProducer().getName() + " " + clickedData.getName();
            Toast.makeText(MainActivity.this, carString, Toast.LENGTH_SHORT).show();
        }
    }
};

