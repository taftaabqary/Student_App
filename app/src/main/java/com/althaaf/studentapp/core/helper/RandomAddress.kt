package com.althaaf.studentapp.core.helper

import com.althaaf.studentapp.core.data.local.model.AddressModel

object RandomAddress {
    private val dummyListAddress: List<AddressModel> = listOf(
        AddressModel("Jl. Merdeka No. 123", "Jakarta Selatan", "DKI Jakarta", "12345"),
        AddressModel("Jl. Pahlawan No. 456", "Bandung", "Jawa Barat", "67890"),
        AddressModel("Jl. Jenderal Sudirman No. 789", "Surabaya", "Jawa Timur", "54321"),
        AddressModel("Jl. Diponegoro No. 101", "Yogyakarta", "DI Yogyakarta", "98765"),
        AddressModel("Jl. Gajah Mada No. 202", "Medan", "Sumatera Utara", "13579")
    )
    fun getRandomAddress() : AddressModel {
        return dummyListAddress.random()
    }
}

