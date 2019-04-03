package intellinectsschool.intellinects.com.v4flavors.Fragments


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import intellinectsschool.intellinects.com.v4flavors.R


/**
 * A simple [Fragment] subclass.
 */
class SwipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }
}// Required empty public constructor
