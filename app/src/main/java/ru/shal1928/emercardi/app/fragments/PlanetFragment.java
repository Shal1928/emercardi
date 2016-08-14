package ru.shal1928.emercardi.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import ru.shal1928.emercardi.app.R;
import ru.shal1928.emercardi.app.activities.MainActivity;

import java.util.Locale;

/**
 * Created by shal1928 on 17.07.2016.
 */
public class PlanetFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";

    public PlanetFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_planet, container, false);

        int i = getArguments().getInt(ARG_PLANET_NUMBER);
        String planet = getResources().getStringArray(R.array.drawer_elements_array)[i];

        int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
            "drawable", getActivity().getPackageName());
        ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);

//        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
//        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
//        ((MainActivity)getActivity()).getSupportActionBar().setElevation(5);
//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        getActivity().setTitle(planet);
        return rootView;
    }
}
