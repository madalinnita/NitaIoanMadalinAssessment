import com.google.gson.annotations.SerializedName

data class Timelines (
	@SerializedName("timeline_id") val timeline_id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("is_primary") val is_primary : Boolean,
	@SerializedName("is_terminal") val is_terminal : Boolean,
	@SerializedName("created") val created : String,
	@SerializedName("updated") val updated : String,
	@SerializedName("chats") val chats : List<Chats>,
	@SerializedName("events") val events : List<Events>
)