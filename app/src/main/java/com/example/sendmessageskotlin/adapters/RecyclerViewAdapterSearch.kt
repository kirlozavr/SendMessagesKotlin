package com.example.sendmessageskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.common.OnClickListener
import com.example.sendmessageskotlin.dto.SearchDto

class RecyclerViewAdapterSearch constructor(
    context: Context,
    onClickListener: OnClickListener<SearchDto>
): RecyclerView.Adapter<RecyclerViewAdapterSearch.ViewHolderSearch>() {

    private val onClickListener: OnClickListener<SearchDto>
    private var searchList: MutableList<SearchDto> = ArrayList()
    private val layoutInflater: LayoutInflater

    init {
        this.onClickListener = onClickListener
        this.layoutInflater = LayoutInflater.from(context)
    }

    fun setList(searchList: MutableList<SearchDto>){
        this.searchList = searchList
        notifyDataSetChanged()
    }

    fun deleteList(){
        this.searchList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSearch {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_search_card, parent, false)
        return ViewHolderSearch(view)
    }

    override fun onBindViewHolder(holder: ViewHolderSearch, position: Int) {
        val searchDto = searchList[position]
        holder.textViewSearch.text = searchDto.getUsername()
        holder.itemView.setOnClickListener(View.OnClickListener {
            onClickListener.onClick(searchDto, position)
        })
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    class ViewHolderSearch(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textViewSearch: TextView

        init {
            textViewSearch = itemView.findViewById(R.id.textViewSearch)
        }
    }
}