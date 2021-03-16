import com.google.gson.annotations.SerializedName

data class PurchasedItems (
	@SerializedName("exchange_type") val exchange_type : String,
	@SerializedName("exchange_key") val exchange_exchange_key : Int
)