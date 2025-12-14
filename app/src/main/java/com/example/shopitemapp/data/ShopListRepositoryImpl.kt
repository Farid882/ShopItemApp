package com.example.shopitemapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopitemapp.domain.ShopItem
import com.example.shopitemapp.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private val shopListLD = MutableLiveData<List<ShopItem>>()

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    init {
          for (i in 0..10){
              addShopItem(ShopItem("Name $i", i, true))
          }
    }
    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Cannot find shop item by id $shopItemId")
    }
    var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id== ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        shopListLD.value = shopList.toList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        shopListLD.value = shopList.toList()
    }
}