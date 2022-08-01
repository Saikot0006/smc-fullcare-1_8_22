package com.gs.smc_mmc.adepter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gs.smc_mmc.fragment.previousMonthHomeFragment;
import com.gs.smc_mmc.fragment.pwRecordHomeFragment;
import com.gs.smc_mmc.fragment.stockRecordHomeFragment;
import com.gs.smc_mmc.fragment.thisMonthHomeFragment;

import org.jetbrains.annotations.NotNull;

public class FragmentAdepter extends FragmentStateAdapter {


    public FragmentAdepter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {


            case 1 :
                return new stockRecordHomeFragment();

                case 2 :
                return new thisMonthHomeFragment();

                case 3 :
                return new previousMonthHomeFragment();
        }
        return new pwRecordHomeFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
