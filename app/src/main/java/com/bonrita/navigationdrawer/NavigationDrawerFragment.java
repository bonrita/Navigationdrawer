package com.bonrita.navigationdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements DrawerAdapter.AdapterClickListener {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private DrawerAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.drawer_list);
        adapter = new DrawerAdapter(getData(), getActivity());

        adapter.setAdapterClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public void setUp(DrawerLayout drawerLayout, Toolbar toolbar) {
        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // Add the humberger menu.
        // The hamburger menu is a button which toggles the drawer on and off.
        // Resource: https://www.udemy.com/android-material-design-zero-to-hero/learn/v4/t/lecture/2664222
        // Resource ID: Navigation drawer: finishing the setup.
        // Resource time: 09:21
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static List<DrawerListItem> getData() {
        List<DrawerListItem> data = new ArrayList<>();

        int[] icons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        String[] titles = {"Google", "Kampala", "Youtube", "Bona"};

        for (int i = 0; i < titles.length; i++) {
            DrawerListItem current = new DrawerListItem();

            current.iconId = icons[i];
            current.title = titles[i];

            data.add(current);
        }

        return data;
    }

    @Override
    public void itemClicked(View view, int position) {
//        Toast.makeText(view.getContext(),"Hold me frag "+ (position + 1), Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), SubActivity.class));
    }
}
