package br.com.devjmcn.desafioguarani.presenter.clients.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CnpjFragment();
            case 1:
                return new CpfFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Número de abas
    }
}