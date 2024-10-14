package com.minjin.musinsa.data.model.component

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type")
internal abstract class UiType {
    abstract val type: String
}

@Serializable
@SerialName("unknown")
internal data class UnknownTypeContent(
    override val type: String = "unknown"
) : UiType()