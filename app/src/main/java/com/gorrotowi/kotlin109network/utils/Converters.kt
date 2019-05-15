package com.gorrotowi.kotlin109network.utils

import com.gorrotowi.kotlin109network.entities.ItemBook
import com.gorrotowi.kotlin109network.network.entitys.PayloadItemTicker
import com.gorrotowi.kotlin109network.network.entitys.ResponseTicker

fun PayloadItemTicker.convertToItemBook(): ItemBook = ItemBook(this.book ?: "", this.last ?: "")

fun ResponseTicker.convertToItemBookList(): List<ItemBook?>? = this.payload?.map { it?.convertToItemBook() }