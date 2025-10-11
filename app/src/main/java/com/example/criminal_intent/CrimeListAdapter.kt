package com.example.criminal_intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.criminal_intent.databinding.ListItemCrimeBinding
import com.example.criminal_intent.databinding.ListItemCrimePoliceBinding
import android.text.format.DateFormat
import java.util.UUID

private const val NORMAL_VIEW_TYPE = 0
private const val SERIOUS_VIEW_TYPE = 1


class CrimeHolder (
    val binding: ListItemCrimeBinding

): RecyclerView.ViewHolder(binding.root){

    fun bind(crime:Crime,onCrimeClicked:(crimeId: UUID)-> Unit){

        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()


        binding.root.setOnClickListener{

            /* Toast.makeText(

                 binding.root.context,
                 "${crime.title} clicked",
                 Toast.LENGTH_SHORT
             ).show()*/
            onCrimeClicked(crime.id)
        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}



class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCrimeClicked:(crimeId:UUID)-> Unit): RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)


    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {

        val crime = crimes[position]
        /* holder.apply {
             binding.crimeTitle.text = crime.title
             binding.crimeDate.text = crime.date.toString()

         }*/

        holder.bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimes.size
}
/*
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
}*/
