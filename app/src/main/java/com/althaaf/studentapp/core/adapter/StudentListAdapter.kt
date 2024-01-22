package com.althaaf.studentapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.althaaf.studentapp.R
import com.althaaf.studentapp.core.data.network.response.DataItem
import com.althaaf.studentapp.databinding.FoItemStudentBinding
import com.bumptech.glide.Glide

class StudentListAdapter: PagingDataAdapter<DataItem, StudentListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: FoItemStudentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataItem) {
            Glide.with(itemView.context)
                .load(item.avatar)
                .into(binding.avatarStudent)

            binding.fullnameStudent.text = itemView.context.getString(R.string.fo_fullname_student, item.firstName, item.lastName)
            binding.addressStudent.text = "Perumahan Duta Bintaro, Kluster Nusa Dua, F10 NO 01"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FoItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}