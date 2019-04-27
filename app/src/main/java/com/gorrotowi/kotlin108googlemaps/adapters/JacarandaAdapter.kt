package com.gorrotowi.kotlin108googlemaps.adapters

import android.content.Context
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import com.gorrotowi.kotlin108googlemaps.R
import com.gorrotowi.kotlin108googlemaps.entitys.JacarandaItem
import kotlinx.android.synthetic.main.item_jacaranda.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import kotlin.properties.Delegates

class JacarandaAdapter : RecyclerView.Adapter<JacarandaAdapter.JacarandaViewHolder>() {


    lateinit var onItemClickLtnr: (Int, JacarandaItem) -> Unit
    var dataSource: List<JacarandaItem> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JacarandaViewHolder {
        return JacarandaViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_jacaranda,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = dataSource.size

    override fun onBindViewHolder(holder: JacarandaViewHolder, position: Int) {
        holder.bindView(dataSource[position])
        holder.itemView.btnItemCard?.setOnClickListener {
            onItemClickLtnr(position, dataSource[position])
        }
    }

    override fun onViewRecycled(holder: JacarandaViewHolder) {
        super.onViewRecycled(holder)
        holder.job.cancel()
    }
    fun setOnItemClickListener(block: (Int, JacarandaItem) -> Unit) {
        onItemClickLtnr = block
    }

    class JacarandaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var job = Job()
        val uiViewHolderScope = CoroutineScope(Dispatchers.Main + job)

        fun bindView(data: JacarandaItem) {
            itemView.imgItemCardHeader?.setImageResource(R.drawable.jacaranda_placeholder)
            itemView.txtItemCardTitle?.text = "Jacaranda ${data.id}"
//            itemView.txtItemCardAddress?.text = "${data.toString()}"

            job = uiViewHolderScope.launch(Dispatchers.IO) {
                val address = getGeocoderAddress(itemView.context, data.location)
                launch(Dispatchers.Main) {
                    itemView.txtItemCardAddress?.text = "$address"
                }

//                val address = async { getGeocoderAddress(itemView.context, data.location) }
//                launch(Dispatchers.Main) {
//                    itemView.txtItemCardAddress?.text = "${address.await()}"
//                }
            }

        }

        suspend fun getGeocoderAddress(ctx: Context, location: LatLng): String {
            val geocoder = Geocoder(ctx, Locale.getDefault())
            return try {
                val listAddress = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                if (listAddress.isNotEmpty()) {
                    val singleAddress = listAddress[0]
                    val addressFragments = with(singleAddress) {
                        (0..maxAddressLineIndex).map { getAddressLine(it) }
                    }
                    addressFragments.joinToString(separator = "\n")
                } else {
                    "La dirección de esta ubicación no esta disponible"
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                "El servicio no esta disponible"
            } catch (illegalArgument: IllegalArgumentException) {
                illegalArgument.printStackTrace()
                "LatLng invalida, intenta nuevamente"
            } catch (e: Exception) {
                e.printStackTrace()
                "General exception"
            }
        }

    }

}