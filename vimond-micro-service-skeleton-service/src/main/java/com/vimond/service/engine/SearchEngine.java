package com.vimond.service.engine;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.client.AssetClient;
import com.vimond.service.client.CategoriesClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thelo on 18/10/16.
 */
public class SearchEngine {

    private AssetClient assetsClient;

    private CategoriesClient categoriesClient;

    public SearchEngine(AssetClient assetClient, CategoriesClient categoriesClient){
        this.categoriesClient = categoriesClient;
        this.assetsClient = assetClient;
    }

    public List<Asset> searchByString(String pattern){

        // Add some logic here.

        return new ArrayList<Asset>();
    }

}
