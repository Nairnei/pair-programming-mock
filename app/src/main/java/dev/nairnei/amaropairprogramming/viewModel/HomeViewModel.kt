package dev.nairnei.amaropairprogramming.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nairnei.amaropairprogramming.R
import dev.nairnei.amaropairprogramming.model.StoreModel
import dev.nairnei.amaropairprogramming.repository.AmaroRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var _repository: AmaroRepository = AmaroRepository()
    private lateinit var _context: Context

    private var _liveDataStore = MutableLiveData<StoreModel>()
    val liveDataStoreModel: LiveData<StoreModel> get() = _liveDataStore

    private var _liveDataError = MutableLiveData<String>()
    val liveDataError: LiveData<String> get() = _liveDataError

    private var _liveDataLoading = MutableLiveData<Boolean>()
    val liveDataLoading: LiveData<Boolean> get() = _liveDataLoading

    fun setContext(context: Context)
    {
        _context = context
    }

    fun listShop() {
        try {
            _liveDataLoading.postValue(true)
            _repository.getStore().enqueue(object : Callback<StoreModel> {
                override fun onFailure(call: Call<StoreModel>, error: Throwable) {
                    showErrorLoadingStore(error.message.toString())
                    getDataFromCache()
                }

                override fun onResponse(
                    call: Call<StoreModel>,
                    response: Response<StoreModel>
                ) {
                    if (response.code() != 200) {
                        getDataFromCache()
                    } else {
                        _liveDataStore.postValue(response.body())
                        _liveDataLoading.postValue(false)
                    }

                }
            })
        } catch (error: Throwable) {

            _liveDataError.postValue(error.message.toString())
            _liveDataLoading.postValue(false)

        }

    }

    private fun getDataFromCache() {
        try {
            _repository.getFromCache().enqueue(object : Callback<StoreModel> {
                override fun onFailure(call: Call<StoreModel>, error: Throwable) {
                    ///todo internacionalizar
                    showErrorLoadingStore(_context.getString(R.string.ops))
                }

                override fun onResponse(
                    call: Call<StoreModel>,
                    response: Response<StoreModel>
                ) {
                    ///todo internacionalizar
                    showErrorLoadingStore(_context.getString(R.string.dataFromCache))
                    _liveDataStore.postValue(response.body())
                    _liveDataLoading.postValue(false)
                }
            })
        } catch (error: Throwable) {
            _liveDataError.postValue(error.message)
            _liveDataLoading.postValue(false)

        }
    }

    private fun showErrorLoadingStore(error: String) {
        _liveDataError.postValue(error)
        _liveDataLoading.postValue(false)
    }

}