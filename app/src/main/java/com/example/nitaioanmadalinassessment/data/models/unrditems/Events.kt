import com.google.gson.annotations.SerializedName

data class Events (
	@SerializedName("type") val type : String,
	@SerializedName("sequence") val sequence : Int,
	@SerializedName("data") val data : Data,
	@SerializedName("has_options") val has_options : Boolean
)