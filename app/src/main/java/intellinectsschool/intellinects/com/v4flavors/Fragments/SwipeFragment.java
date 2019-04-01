package intellinectsschool.intellinects.com.v4flavors.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import intellinectsschool.intellinects.com.v4flavors.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends Fragment {

    public SwipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }
}
