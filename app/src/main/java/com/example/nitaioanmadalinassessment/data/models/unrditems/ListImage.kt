import com.google.gson.annotations.SerializedName

data class ListImage (
	@SerializedName("resource_id") val resource_id : Int,
	@SerializedName("resource_fid") val resource_fid : String,
	@SerializedName("resource_type") val resource_type : String,
	@SerializedName("resource_uri") val resource_uri : String,
	@SerializedName("resource_preset") val resource_preset : String,
	@SerializedName("resource_processed") val resource_processed : Boolean,
	@SerializedName("resource_progress") val resource_progress : Int
)