package dev.nairnei.amaropairprogramming.viewModelAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.nairnei.amaropairprogramming.databinding.InfalteStoreBinding
import dev.nairnei.amaropairprogramming.model.StoreModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.StoreViewHolder>() {


    lateinit var storeModelList: StoreModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            InfalteStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class StoreViewHolder(val binding: InfalteStoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = storeModelList[position]
        holder.binding.apply {
            textView.text = store.name
        }
    }

    override fun getItemCount(): Int {
        return storeModelList.size
    }


}