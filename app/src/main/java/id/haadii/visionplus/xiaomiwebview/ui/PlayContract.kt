package id.haadii.visionplus.xiaomiwebview.ui

import id.haadii.visionplus.xiaomiwebview.model.XiaomiResponse

/**
 * Created by nurrahmanhaadii on 15,July,2020
 */
interface PlayContract {
    interface Presenter {
        fun getData()
    }
    interface View {
        fun getDataSuccess(data: XiaomiResponse)
        fun getDataFailed()
    }
}