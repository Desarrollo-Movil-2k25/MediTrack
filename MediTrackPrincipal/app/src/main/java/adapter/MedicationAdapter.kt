package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meditrackprincipal.R
import entity.Medication

class MedicationAdapter(private val medications: List<Medication>) :
    RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>() {
    class MedicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvMedName)
        val tvDose: TextView = itemView.findViewById(R.id.tvMedDose)
        val tvDescription: TextView = itemView.findViewById(R.id.tvMedDescription)
        val tvFrequency: TextView = itemView.findViewById(R.id.tvMedFrequency)
        val tvTime: TextView = itemView.findViewById(R.id.tvMedTime)
        val tvStartDate: TextView = itemView.findViewById(R.id.tvMedStartDate)
        val tvEndDate: TextView = itemView.findViewById(R.id.tvMedEndDate)
        val imgMedication: ImageView = itemView.findViewById(R.id.imgMedication)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medication, parent, false)
        return MedicationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val med = medications[position]

        // Nombre
        holder.tvName.text = med.name

        // Dosis
        holder.tvDose.text = "Dosis: ${med.dose}"

        // Descripción
        holder.tvDescription.text =
            if (!med.description.isNullOrEmpty()) med.description else "Sin descripción"

        // Frecuencia
        holder.tvFrequency.text = "Frecuencia: ${med.frequency}"

        // Hora
        holder.tvTime.text = "Hora: ${med.time}"

        // Fecha inicio
        holder.tvStartDate.text = "Inicio: ${med.startDate}"

        // Fecha fin (solo si existe)
        if (med.endDate != null) {
            holder.tvEndDate.text = "Fin: ${med.endDate}"
            holder.tvEndDate.visibility = View.VISIBLE
        } else {
            holder.tvEndDate.visibility = View.GONE
        }

        holder.imgMedication.setImageResource(R.drawable.pill_default)

        /*if (med.image != null) {
            holder.imgMedication.setImageBitmap(med.image)
        } else {
            holder.imgMedication.setImageResource(R.drawable.pill_default)
        }*/
    }

    override fun getItemCount(): Int = medications.size
}
