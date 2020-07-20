package id.haadii.visionplus.xiaomiwebview.api

import id.haadii.visionplus.xiaomiwebview.model.XiaomiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by nurrahmanhaadii on 14,July,2020
 */
interface ApiService {
    @GET
    fun getData(@Url url: String) : Call<XiaomiResponse>
}