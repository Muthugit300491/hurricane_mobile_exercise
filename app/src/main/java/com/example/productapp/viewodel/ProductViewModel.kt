package com.example.productapp.viewodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aezion.aerouting.driver.data.repository.MainRepository
import com.aezion.aerouting.driver.data.repository.Resource
import com.example.productapp.data.api.NetworkHelper
import com.example.productapp.data.model.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val mainRepository: MainRepository,private val networkHelper: NetworkHelper): ViewModel() {

    private fun getProduct(
        mLiveData: MutableLiveData<Resource<ProductDetails>>
    ) {
        viewModelScope.launch {
            mLiveData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getProduct()
                    .catch { e ->
                        mLiveData.postValue(Resource.error(e.toString(), null))
                    }
                    .collect {
                        mLiveData.postValue(Resource.success(it))
                    }
            } else {
                mLiveData.postValue(Resource.error("No internet connection", null))
            }
        }
    }

    fun getProductDetails(
        mLiveData: MutableLiveData<Resource<ProductDetails>>
    ): LiveData<Resource<ProductDetails>> {
        getProduct(mLiveData)
        return mLiveData
    }
}