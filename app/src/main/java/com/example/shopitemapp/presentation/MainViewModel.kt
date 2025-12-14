package com.example.shopitemapp.presentation

import androidx.lifecycle.ViewModel
import com.example.shopitemapp.data.ShopListRepositoryImpl
import com.example.shopitemapp.domain.DeleteShopItemUseCase
import com.example.shopitemapp.domain.EditeShopItemUseCase
import com.example.shopitemapp.domain.GetShopListUseCase
import com.example.shopitemapp.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditeShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopListLD = getShopListUseCase.getShopList()

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
}