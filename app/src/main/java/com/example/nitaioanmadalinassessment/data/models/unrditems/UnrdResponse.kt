import com.google.gson.annotations.SerializedName

data class UnrdResponse (
	@SerializedName("status") val status : Status,
	@SerializedName("result") val result : Result
)