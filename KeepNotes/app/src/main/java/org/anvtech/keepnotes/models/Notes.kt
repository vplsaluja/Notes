package org.anvtech.keepnotes.models

data class Notes(
    val id: Long = -1,
    val description: String,
    val title: String,
    val createdOn: String = "",
    val updatedOn: String = ""
)