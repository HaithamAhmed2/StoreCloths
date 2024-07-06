package com.store.cloths.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.store.cloths.data.db.ClothsDAO
import com.store.cloths.data.db.ClothsDatabase
import com.store.cloths.data.db.entities.ClothCartCountEntity
import com.store.cloths.data.db.entities.ClothEntity
import com.store.cloths.data.db.entities.ClothOrderCountEntity
import com.store.cloths.data.db.entities.OrderEntity
import com.store.cloths.data.db.relations.ClothCartCountRelation
import com.store.cloths.data.db.relations.OrderClothRelation
import com.store.cloths.models.Cloth
import com.store.cloths.models.Order
import com.store.cloths.models.Review
import java.util.Date
import java.util.concurrent.ExecutorService

interface ClothsRepository {

    fun getCatalogCloths(): LiveData<List<Cloth>>
    fun getClothDetails(clothId: Long): LiveData<Cloth>
    fun getHistory(): LiveData<List<Order>>
    fun getCart(): LiveData<List<Cloth>>
    fun getOrderDetails(orderId: Long): LiveData<Pair<Order, List<Cloth>>>
    fun addToCart(clothId: Long, count: Int)
    fun makeOrder(clothsIds: List<Pair<Long, Int>>): LiveData<Long>
    fun getClothReviews(clothId: Long): LiveData<List<Review>>

    class Impl(
        val clothsDAO: ClothsDAO,
        val database: ClothsDatabase,
        val executorService: ExecutorService,
        ) : com.store.cloths.data.ClothsRepository {
        override fun getCatalogCloths(): LiveData<List<Cloth>> {
            return clothsDAO.getCatalogCloths().map { clothEntities ->
                clothEntities.map(ClothCartCountRelation::toModel)
            }
        }

        override fun getClothDetails(clothId: Long): LiveData<Cloth> {
            return clothsDAO.getClothDetails(clothId).map {
                it.toModel()
            }
        }

        override fun getHistory(): LiveData<List<Order>> {
            return clothsDAO.getHistory().map { orderEntities ->
                orderEntities.map(OrderEntity::toModel)
            }
        }

        override fun getCart(): LiveData<List<Cloth>> {
            return clothsDAO.getShoppingCart().map { cart ->
                cart.map { it.toModel() }
            }
        }

        override fun getOrderDetails(orderId: Long): LiveData<Pair<Order, List<Cloth>>> {
            val liveData = MutableLiveData<Pair<Order, List<Cloth>>>()
            executorService.submit {
                val order = clothsDAO.getOrderById(orderId).toModel()
                val cloths = clothsDAO.getOrderCloths(orderId).map(OrderClothRelation::toModel)

                liveData.postValue(order to cloths)
            }
            return liveData
        }

        override fun addToCart(clothId: Long, count: Int) {
            executorService.submit {
                clothsDAO.addToCart(ClothCartCountEntity(clothId, count.coerceIn(0..30)))
            }
        }

        override fun makeOrder(clothsIds: List<Pair<Long, Int>>): LiveData<Long>{
            val liveData = MutableLiveData<Long>()
            executorService.submit {
                var orderId = 0L
                database.runInTransaction {
                    val cloths = clothsDAO.getCloths(clothsIds.map {
                        it.first
                    })
                    val orderEntity = OrderEntity(
                        date = Date(),
                        quantityCloths = cloths.size,
                        totalPrice = cloths.foldIndexed(0) { index,acc, cloth ->
                            acc + cloth.price * clothsIds[index].second
                                                          },
                    )
                    orderId = clothsDAO.insertOrder(orderEntity)
                    val orderClothEntities = cloths.mapIndexed { index: Int, clothEntity: ClothEntity ->
                        ClothOrderCountEntity(
                            orderId = orderId,
                            clothId = clothEntity.id,
                            count = clothsIds[index].second
                        )
                    }
                    clothsDAO.insertOrderCloths(orderClothEntities)
                    clothsDAO.clearShoppingCart()
                }
                liveData.postValue(orderId)
            }
            return liveData
        }

        override fun getClothReviews(clothId: Long): LiveData<List<Review>> {
            return clothsDAO.getClothReviews(clothId).map { reviewEntities ->
                reviewEntities.map { it.toModel() }
            }
        }

    }
}