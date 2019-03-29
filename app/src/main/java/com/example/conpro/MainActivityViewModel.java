package com.example.conpro;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<String>> itemList;

    LiveData<List<String>> getItemList() {
        if (itemList == null) {
            itemList = new MutableLiveData<>();
            loadItems();
        }
        return itemList;
    }

    public void addItem( String item) {
        List<String> itemsList;
        itemsList = itemList.getValue();
        itemsList.add(item);
        itemList.setValue(itemsList);

    }


    public void removeItem() {
        List<String> itemsList = itemList.getValue();
        if (itemsList.size() != 0){
            itemsList.remove(itemsList.size()-1);
            itemList.setValue(itemsList);
        }
    }


    private void loadItems() {
        List<String> fruitsStringList = new ArrayList<>();
        itemList.setValue(fruitsStringList);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

}