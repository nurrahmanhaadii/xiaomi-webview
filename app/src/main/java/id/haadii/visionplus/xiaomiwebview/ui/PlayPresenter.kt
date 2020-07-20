package id.haadii.visionplus.xiaomiwebview.ui

import id.haadii.visionplus.xiaomiwebview.model.XiaomiRepository

/**
 * Created by nurrahmanhaadii on 15,July,2020
 */
class PlayPresenter(baseUrl: String, headerOne: String, headerTwo: String, private val view: PlayContract.View): PlayContract.Presenter {
    val repository = XiaomiRepository(baseUrl, headerOne, headerTwo)

    override fun getData() {
        repository.getData {
            if (it != null) {
                view.getDataSuccess(it)
            } else {
                view.getDataFailed()
            }
        }
    }
}