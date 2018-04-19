package com.stdesign.bitacorasutd.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stdesign.bitacorasutd.helper.SharedPrefManager;
import com.stdesign.bitacorasutd.model.User;
import com.stdesign.bitacorasutd.spinners_adapters.CustomAdapter;
import com.stdesign.bitacorasutd.activities.MainActivity;
import com.stdesign.bitacorasutd.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CuentaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
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

    private Spinner sp1;
    private String [] names = {"Idioma","Español(México)","English(USA)"};
    private int [] images = {R.drawable.lenguaje, R.drawable.mexico, R.drawable.united_states};
    private CustomAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);
        //Connect java and xml components
        sp1 = view.findViewById(R.id.splenguaje);
        ImageView IcerrarS = view.findViewById(R.id.ivcerrarsesion);
        TextView TcerrarS = view.findViewById(R.id.tvcerrarsesion);
        TextView usuario = view.findViewById(R.id.tvusuario);

        User user = SharedPrefManager.getInstance(getContext()).getUser();
        usuario.setText(user.getName()+" "+user.getLastname());


        //Set functions to the elements
        adapter = new CustomAdapter(view.getContext(),names,images);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    Toast.makeText(view.getContext(),"Idioma Español seleccionado",Toast.LENGTH_SHORT).show();
                }//Close IF i==0

                if(i == 2){
                    Toast.makeText(view.getContext(),"Idioma Inglés seleccionado",Toast.LENGTH_SHORT).show();
                }//Close IF i==0
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        IcerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        TcerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               logout();
            }
        });

        return view;
    }//Close method onCreateView

    private void logout() {
        SharedPrefManager.getInstance(getContext()).logout();
        getActivity().finish();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
