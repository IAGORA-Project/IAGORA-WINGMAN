import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.process_order.presentation.R
import com.iagora.wingman.process_order.presentation.databinding.ActivityProcessOrderBinding

class ProcessOrderActivity :
    BaseActivity<ActivityProcessOrderBinding>({ ActivityProcessOrderBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}