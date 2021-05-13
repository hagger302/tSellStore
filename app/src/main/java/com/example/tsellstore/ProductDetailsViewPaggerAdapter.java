package com.example.tsellstore;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ProductDetailsViewPaggerAdapter extends FragmentPagerAdapter {

    int totalTabs;
    private String productDescription;
    private String productOtherDetails;
    private List<ProducSpecificationItemModel> specificationItemModelList;
    public ProductDetailsViewPaggerAdapter(@NonNull FragmentManager fm,int totalTabs,String productDescription,String productOtherDetails,List<ProducSpecificationItemModel> specificationItemModelList) {
        super(fm);
        this.totalTabs = totalTabs;
        this.productDescription = productDescription;
        this.productOtherDetails = productOtherDetails;
        this.specificationItemModelList = specificationItemModelList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProductDescriptionFragment descriptionFragment1 = new ProductDescriptionFragment();
                descriptionFragment1.body = productDescription;
                return descriptionFragment1;
            case 1:
                ProductSpecificationFragment specificationFragment = new ProductSpecificationFragment();
                specificationFragment.specificationItemModelList = specificationItemModelList;
                return specificationFragment;
            case 2:
                ProductDescriptionFragment descriptionFragment2 = new ProductDescriptionFragment();
                descriptionFragment2.body = productOtherDetails;
                return descriptionFragment2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
