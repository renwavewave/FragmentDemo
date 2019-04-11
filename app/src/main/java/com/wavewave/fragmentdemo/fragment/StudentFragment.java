package com.wavewave.fragmentdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wavewave.fragmentdemo.R;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public StudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentFragment newInstance(String param1, String param2) {
        StudentFragment fragment = new StudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_student, container, false);
        initView(inflate);
        return inflate;

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("aaaaa", "student onResume");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("aaaaa", "student isVisibleToUser:" + isVisibleToUser);
    }

    private void initView(View view) {
        ListView student_f_lv = view.findViewById(R.id.student_f_lv);
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            linkedList.add("wave_wave_" + i);
        }
        student_f_lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, linkedList));
    }


}
