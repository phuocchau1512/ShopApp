package com.example.shopapp.model

import android.os.Parcel
import android.os.Parcelable

data class ItemsModel(
    var categoryId: String = "",
    var description: String = "",
    var model: ArrayList<String> = ArrayList(),
    var picUrl: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numberInCart: Int = 0,
    var showRecommended: Boolean = false,
    var title: String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString().toString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(categoryId)
        dest.writeString(description)
        dest.writeStringList(model)
        dest.writeStringList(picUrl)
        dest.writeDouble(price)
        dest.writeDouble(rating)
        dest.writeInt(numberInCart)
        dest.writeByte(if (showRecommended) 1 else 0)
        dest.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsModel> {
        override fun createFromParcel(parcel: Parcel): ItemsModel {
            return ItemsModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsModel?> {
            return arrayOfNulls(size)
        }
    }
}
