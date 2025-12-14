package com.example.shopitemapp.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private val shopListRepository: ShopListRepository){
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}