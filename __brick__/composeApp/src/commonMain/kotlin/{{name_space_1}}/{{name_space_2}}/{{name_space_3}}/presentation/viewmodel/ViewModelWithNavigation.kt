package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination

abstract class ViewModelWithNavigation : ViewModel() {
    private val _destination = MutableSharedFlow<NavigationDestination>()
    val destination: Flow<NavigationDestination> = _destination

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setIsLoading(value: Boolean) {
        _isLoading.value = value
    }

    protected suspend fun navigateTo(destination: NavigationDestination) {
        _destination.emit(destination)
    }
}
