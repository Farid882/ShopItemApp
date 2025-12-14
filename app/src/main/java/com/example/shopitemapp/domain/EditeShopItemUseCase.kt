package com.example.shopitemapp.domain

class EditeShopItemUseCase (private val shopListRepository: ShopListRepository){
    fun editShopItem(shopItem: ShopItem){
       shopListRepository.editShopItem(shopItem)
    }
}