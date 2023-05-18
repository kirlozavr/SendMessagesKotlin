package com.example.sendmessageskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sendmessageskotlin.R
import com.example.sendmessageskotlin.common.OnClickListener
import com.example.sendmessageskotlin.dto.ChatsDto

class RecyclerViewAdapterChats(
    context: Context,
    onClickListener: OnClickListener<ChatsDto>
): RecyclerView.Adapter<RecyclerViewAdapterChats.RecyclerViewHolder>() {

    private var inflater: LayoutInflater
    private var onClickListener: OnClickListener<ChatsDto>
    private var chatsList: MutableList<ChatsDto> = ArrayList<ChatsDto>()

    init {
        this.onClickListener = onClickListener
        this.inflater = LayoutInflater.from(context)
    }

    fun setList(chatsList: MutableList<ChatsDto>){
        this.chatsList = chatsList
        notifyDataSetChanged()
    }

    fun deleteList(){
        this.chatsList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_chat_card, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatsList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        var chatsDto: ChatsDto = chatsList.get(position)

        if(
            chatsDto
                .getLastMessage()
                .trim().isEmpty()
        ){
            holder.textLastMessage.text = "Изображение"
        } else{
            holder.textLastMessage.text = chatsDto.getLastMessage()
        }

        holder.textChatsName.text = chatsDto.getUsernameToWhom()
        holder.timeMessage.text = chatsDto.getTimeMessageToChats()
        holder.itemView.setOnClickListener {
            onClickListener.onClick(chatsDto, position)
        }
    }

    class RecyclerViewHolder: RecyclerView.ViewHolder {

        val textChatsName: TextView
        val textLastMessage: TextView
        val timeMessage: TextView

        constructor(itemView: View) : super(itemView) {
            textChatsName = itemView.findViewById(R.id.textChatsName)
            textLastMessage = itemView.findViewById(R.id.textLastMessages)
            timeMessage = itemView.findViewById(R.id.timeMessage)
        }
    }
}