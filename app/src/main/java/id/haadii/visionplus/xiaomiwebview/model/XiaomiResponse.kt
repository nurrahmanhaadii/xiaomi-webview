package id.haadii.visionplus.xiaomiwebview.model

/**
 * Created by nurrahmanhaadii on 14,July,2020
 */
data class XiaomiResponse(
    val all_count: Int,
    val data: List<Data>,
    val page_count: Int,
    val status: String
)

data class Data(
    val area: String,
    val episodes: List<Episode>
)

data class Episode(
    val play_info: PlayUrl,
    val title: String
)

data class PlayUrl(
    val play_url: String
)