package com.example.fluently

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fluently.databinding.ButtonLevelItemBinding
import com.google.android.material.internal.ContextUtils.getActivity

class ButtonLevelAdapter(private val list:List<ButtonLevel>): RecyclerView.Adapter<ButtonLevelAdapter.ButtonLevelHolder>() {

    /*new*/
    private val searchBtn= ArrayList<ButtonLevel>()
    var txt=0
    /**/

    class ButtonLevelHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ButtonLevelItemBinding.bind(item)
        fun bind(buttonLevel:ButtonLevel)=with(binding){
            button.text=buttonLevel.title
        }
        val text = item.findViewById<Button>(R.id.button)!!


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonLevelHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_level_item, parent, false)

        return ButtonLevelHolder(view)

    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: ButtonLevelHolder, position: Int) {
        holder.bind(list[position])
        holder.text.text = list[position].title
        when((getActivity(holder.itemView.context) as EasyLevelActivity).difcolor()){
            "greenBtnColor"-> holder.text.setBackgroundColor(Color.parseColor("#00C140"))
            "yellowBtnColor"-> holder.text.setBackgroundColor(Color.parseColor("#DFBB00"))
            "redBtnColor"-> holder.text.setBackgroundColor(Color.parseColor("#FF0202"))
        }
        //holder.itemView.context   //old
        /**/
        val context = holder.itemView.context

        holder.binding.button.setOnClickListener {
            when(position){
                0 -> txt="1".toInt()
                1 -> txt="2".toInt()
                2 -> txt="3".toInt()
                3 -> txt="4".toInt()
                4 -> txt="5".toInt()
                5 -> txt="6".toInt()
                6 -> txt="7".toInt()
                7 -> txt="8".toInt()
                8 -> txt="9".toInt()
                9 -> txt="10".toInt()
                10 -> txt="11".toInt()
                11 -> txt="12".toInt()
                12 -> txt="13".toInt()
                13 -> txt="14".toInt()
                14 -> txt="15".toInt()
                15 -> txt="16".toInt()
                16 -> txt="17".toInt()
                17 -> txt="18".toInt()
                18 -> txt="19".toInt()
                19 -> txt="20".toInt()
            }

            //(getActivity(context) as EasyLevelActivity).finishAct(txt)
            (getActivity(context) as EasyLevelActivity).goToLevel(txt)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}