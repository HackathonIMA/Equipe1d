package br.gov.sp.ima.hackathon.monitor156.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Arrays;
import java.util.List;

import br.gov.sp.ima.hackathon.monitor156.fragments.ArchivedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.FinishedMonitoringFragment;
import br.gov.sp.ima.hackathon.monitor156.fragments.InProgressMonitoringFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    public static final List<String> TABS_TITLE = Arrays.asList("Em Andamento", "Finalizados", "Arquivados");

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InProgressMonitoringFragment.newInstance();
            case 1:
                return FinishedMonitoringFragment.newInstance();
            case 2:
                return ArchivedMonitoringFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TABS_TITLE.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TABS_TITLE.get(position);
    }
}