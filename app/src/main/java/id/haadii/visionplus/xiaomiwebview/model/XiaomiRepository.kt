package id.haadii.visionplus.xiaomiwebview.model

import id.haadii.visionplus.xiaomiwebview.api.ApiService
import id.haadii.visionplus.xiaomiwebview.api.ApiServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by nurrahmanhaadii on 15,July,2020
 */
class XiaomiRepository(val baseUrl: String, headerOne: String, headerTwo: String) {

    private var apiService = ApiServiceGenerator(baseUrl).createService(ApiService::class.java, headerOne, headerTwo)

    fun getData(listener: (XiaomiResponse?) -> Unit) {
        apiService.getData(baseUrl)
            .enqueue(object : Callback<XiaomiResponse> {
                override fun onResponse(
                    call: Call<XiaomiResponse>,
                    response: Response<XiaomiResponse>
                ) {
                    listener(response.body())
                }

                override fun onFailure(call: Call<XiaomiResponse>, t: Throwable) {

                }
            })
    }
}