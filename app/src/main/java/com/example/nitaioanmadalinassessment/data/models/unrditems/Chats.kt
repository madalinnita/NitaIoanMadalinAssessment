import com.google.gson.annotations.SerializedName

data class Chats (
	@SerializedName("chat_id") val chat_id : Int,
	@SerializedName("timeline_id") val timeline_id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("price") val price : Int,
	@SerializedName("is_group") val is_group : Boolean,
	@SerializedName("is_locked") val is_locked : Boolean,
	@SerializedName("display_name") val display_name : String,
	@SerializedName("owned") val owned : String
)