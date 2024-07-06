package com.store.cloths.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.store.cloths.models.Cloth

class OrderViewModel : ViewModel() {
    private val testCloths: List<Cloth> = listOf(
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
        Cloth(
            id = 0,
            name = "Сила безгранична",
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiqLv3suUFLTbyBusRPN_Q44hGKDVBa2AA7eiJ7Q_eLXtU3AKWXa_ps6rCAvtM4VH-F0JMwKsM3iz8AlwaUHcA0nMz3fTDsh8NArgsRnn0LRtHouJxVGWNhLA01JBETF_tQhT4U3Fe-9kwrO_twnF3-WZtsXKYHxP9x1QDU_k7IL2YtOZnAo4ZBIIZ3sS8/s1024/8d51e7a3-970c-4a9e-979d-94ff23264ac3.jpg",
            author = "Ван Гог Винсент",
            editionYear = 2024,
            description = "",
            mark = 5f,
            price = 999,
            inCartCount = 15,
        ),
    )

    private val _cloths = MutableLiveData(testCloths)
    val cloths: LiveData<List<Cloth>> = _cloths
}