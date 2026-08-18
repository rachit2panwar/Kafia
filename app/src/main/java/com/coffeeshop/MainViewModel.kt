package com.coffeeshop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffeeshop.core.datastore.UserPrefsDataStore
import com.coffeeshop.core.domain.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userPrefsDataStore: UserPrefsDataStore
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = userPrefsDataStore.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeMode.FOLLOW_SYSTEM
        )
}
