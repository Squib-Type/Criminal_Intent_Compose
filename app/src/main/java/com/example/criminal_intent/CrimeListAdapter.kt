package com.example.criminal_intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminal_intent.databinding.ListItemCrimeBinding
import com.example.criminal_intent.databinding.ListItemCrimePoliceBinding
import android.text.format.DateFormat

private const val NORMAL_VIEW_TYPE = 0
private const val SERIOUS_VIEW_TYPE = 1

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = DateFormat.format("MMMM d, yyyy", crime.date)

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.crimeSolved.visibility = if(crime.isSolved) {
            View.VISIBLE
        }else {
            View.GONE

        }
    }
}

class SeriousCrimeHolder(
    private val binding: ListItemCrimePoliceBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = DateFormat.format("MMMM d, yyyy", crime.date)

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.contactPoliceButton.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Contacting Police for ${crime.title}!",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.crimeSolved.visibility = if(crime.isSolved) {
            View.VISIBLE
        }else {
            View.GONE

        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice) {
            SERIOUS_VIEW_TYPE
        } else {
            NORMAL_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SERIOUS_VIEW_TYPE -> {
                val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                SeriousCrimeHolder(binding)
            }
            else -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is SeriousCrimeHolder -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size
}