package com.skorzh.githubapitask.ui.fragment;

import android.support.v4.app.Fragment;

import com.skorzh.githubapitask.di.component.IHasComponent;

public abstract class BaseFragment extends Fragment {

    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>) getActivity()).getComponent());
    }

}
