package com.iagora.wingman.app.main_menu.presentation.akun

import android.content.Intent
import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.presentation.AuthActivity
import com.iagora.wingman.app.databinding.FragmentProfileBinding
import com.iagora.wingman.app.main_menu.presentation.home.HomeViewModel
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.domain.usecase.ISavePref
import com.iagora.wingman.core.presentation.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile,
    { FragmentProfileBinding.bind(it) }) {

    private val sesPref: SessionManager by inject()

    override fun setView() {
        binding.textNotifications.text = "Logout"
        binding.textNotifications.setOnClickListener {
            sesPref.logout()
            startActivity(
                Intent(
                    context,
                    AuthActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}