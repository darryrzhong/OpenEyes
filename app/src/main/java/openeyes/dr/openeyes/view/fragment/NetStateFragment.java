package openeyes.dr.openeyes.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2018/7/24.
 */

public class NetStateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_net_state,null);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
