package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.domain.entity.NavigationDestination
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.presentation.viewmodel.ViewModelWithNavigation

abstract class MainViewModel : ViewModelWithNavigation() {
    abstract val count: StateFlow<Int>

    abstract fun increaseCount()

    abstract fun navigate()
}

class MainViewModelImpl : MainViewModel() {
    override val count = MutableStateFlow<Int>(0)

    override fun increaseCount() {
        count.value++
    }

    override fun navigate() {
        viewModelScope.launch {
            navigateTo(
                if (count.value.isEven) {
                    NavigationDestination.Even
                } else {
                    NavigationDestination.Odd
                },
            )
        }
    }

    private val Int.isEven get() = this % 2 == 0
}
