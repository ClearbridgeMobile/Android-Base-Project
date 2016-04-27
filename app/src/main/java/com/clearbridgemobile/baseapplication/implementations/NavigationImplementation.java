package com.clearbridgemobile.baseapplication.implementations;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.util.Log;

import com.clearbridgemobile.baseapplication.fragments.BaseFragment;
import com.clearbridgemobile.core.enums.PageId;
import com.clearbridgemobile.core.interfaces.AppNavigationInterface;

import java.util.HashMap;

/**
 * Created by nikitakramarovsky on 15-04-01.
 */
public class NavigationImplementation implements AppNavigationInterface {

    private Activity activity;

    private HashMap<PageId, BaseFragment> fragments;
    private HashMap<PageId, String> fragmentTags;

    private int fragment_container;

    private PageId currentPageId;

    public NavigationImplementation(Activity fragActivity) {
        activity = fragActivity;

        fragments = new HashMap();
        fragmentTags = new HashMap();
    }

    @Override
    public void goToPage(PageId page) {
        if (activity == null) {
            return;
        }

        BaseFragment nextFragment = null;
        String tag = null;

        //init fragment transition
        //TODO replace with the container id
        fragment_container = 0;

        int in_animation = 0, out_animation = 0;

        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(in_animation, out_animation);

        if (fragments.containsKey(page)) {
            //Get fragment
            nextFragment = fragments.get(page);
            tag = nextFragment.getTag();
        }

        //create next fragment
        boolean isOverlayFragment = false;

        switch (page) {
        }

        if (nextFragment == null) {
            Log.e("NULL_POINTER_EXCEPTION", "nextFragment is null something went wrong");
            return;
        }
        Log.d("NAVIGATION", "Going to fragment: " + tag);

        if (tag == null)
            tag = nextFragment.getClass().getSimpleName() + "_" + fragments.size();

        if (isOverlayFragment) {
            transaction.replace(fragment_container, nextFragment, tag);

        } else {
            if (!nextFragment.isAdded() && !nextFragment.isRemoving()) {
                transaction.replace(fragment_container, nextFragment, tag);
            }
        }

        addPage(page, nextFragment, tag);

        transaction.commit();

        currentPageId = page;
    }

    private void addPage(PageId page, BaseFragment nextFragment, String tag) {
        fragments.put(page, nextFragment);
        fragmentTags.put(page, tag);
    }

    public boolean onBackPressed() {
        if (currentPageId != null && fragments.containsKey(currentPageId))
            return (fragments.get(currentPageId)).onBackPressed();

        else
            return true;
    }
}
