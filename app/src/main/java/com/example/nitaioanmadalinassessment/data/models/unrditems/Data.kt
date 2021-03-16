import com.google.gson.annotations.SerializedName

data class Data (
	@SerializedName("chat_message_id") val chat_message_id : Int,
	@SerializedName("chat_id") val chat_id : Int,
	@SerializedName("character_id") val character_id : Int,
	@SerializedName("media_duration") val media_duration : String,
	@SerializedName("content") val content : String,
	@SerializedName("url_label") val url_label : String,
	@SerializedName("sequence") val sequence : Int,
	@SerializedName("price") val price : Int,
	@SerializedName("is_locked") val is_locked : Boolean,
	@SerializedName("has_options") val has_options : Boolean,
	@SerializedName("options_timeout") val options_timeout : Int,
	@SerializedName("created") val created : String,
	@SerializedName("updated") val updated : String,
	@SerializedName("has_url") val has_url : Boolean,
	@SerializedName("resource_id") val resource_id : String,
	@SerializedName("thumb_resource_id") val thumb_resource_id : String,
	@SerializedName("owned") val owned : String
)