package com.store.cloths.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.store.cloths.data.db.entities.ClothCartCountEntity
import com.store.cloths.data.db.relations.ClothCartCountRelation
import com.store.cloths.data.db.entities.ClothEntity
import com.store.cloths.data.db.entities.ClothOrderCountEntity
import com.store.cloths.data.db.relations.OrderClothRelation
import com.store.cloths.data.db.entities.OrderEntity
import com.store.cloths.data.db.entities.ReviewEntity

@Dao
interface ClothsDAO {
    @Transaction
    @Query("""
        SELECT cloths.*, shopping_cart.* FROM cloths
        LEFT JOIN shopping_cart on shopping_cart.clothId = cloths.id
        """)
    fun getCatalogCloths(): LiveData<List<ClothCartCountRelation>>

    @Query("""
        SELECT * FROM cloths WHERE id in(:ids)
    """)
    fun getCloths(ids: List<Long>): List<ClothEntity>

    @Transaction
    @Query("""
        SELECT cloths.*, shopping_cart.* FROM cloths
        LEFT JOIN shopping_cart on shopping_cart.clothId = cloths.id
        WHERE count > 0
        """)
    fun getShoppingCart(): LiveData<List<ClothCartCountRelation>>

    @Query("""DELETE FROM shopping_cart""")
    fun clearShoppingCart()

    @Transaction
    @Query("""
        SELECT * FROM cloths WHERE id = :clothId
        """)
    fun getClothDetails(clothId: Long): LiveData<ClothCartCountRelation>

    @Query("SELECT * FROM orders ORDER BY date DESC")
    fun getHistory(): LiveData<List<OrderEntity>>

    @Query("""SELECT
            order_cloths_count.*,
            cloths.*
            FROM order_cloths_count
            INNER JOIN cloths on cloths.id = order_cloths_count.clothId
            WHERE order_cloths_count.orderId = :orderId
            """)
    fun getOrderCloths(orderId: Long): List<OrderClothRelation>

    @Query("""SELECT * FROM orders WHERE id = :orderId""")
    fun getOrderById(orderId: Long): OrderEntity

    @Query("""SELECT * FROM reviews WHERE clothId = :clothId""")
    fun getClothReviews(clothId: Long): LiveData<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToCart(clothCartCountEntity: ClothCartCountEntity)

    @Insert
    fun insertOrder(orderEntity: OrderEntity): Long

    @Insert
    fun insertReviews(reviews: List<ReviewEntity>)

    @Insert
    fun insertOrderCloths(clothsOrderEntity: List<ClothOrderCountEntity>)

    @Insert
    fun insertCloths(cloths: List<ClothEntity>): List<Long>
}