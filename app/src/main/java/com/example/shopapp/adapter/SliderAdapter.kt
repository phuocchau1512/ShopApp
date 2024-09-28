import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shopapp.databinding.SliderItemContainerBinding
import com.example.shopapp.model.SliderModel

class SliderAdapter(
    private val sliderItems: List<SliderModel>,  // Khai báo `sliderItems` là `private val`
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val runnable = Runnable {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SliderItemContainerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position])
        if (position == sliderItems.size - 1) {
            viewPager2.post(runnable)  // Giữ lại logic lặp lại nếu cần thiết
        }
    }

    override fun getItemCount(): Int = sliderItems.size

    class SliderViewHolder(private val binding: SliderItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setImage(slider: SliderModel) {
            Glide.with(binding.root.context)  // Lấy context từ view
                .load(slider.url)
                .apply(RequestOptions().transform(CenterInside()))
                .into(binding.imageSlider)
        }
    }
}
