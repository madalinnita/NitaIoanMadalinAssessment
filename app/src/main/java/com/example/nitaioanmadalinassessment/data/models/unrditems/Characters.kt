import com.google.gson.annotations.SerializedName

data class Characters (
	@SerializedName("character_id") val character_id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("is_main") val is_main : Boolean,
	@SerializedName("image") val image : Image
)