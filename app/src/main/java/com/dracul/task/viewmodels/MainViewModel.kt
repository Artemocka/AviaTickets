package com.dracul.task.viewmodels

import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.dracul.task.R
import com.dracul.task.di.DaggerInjector
import com.dracul.task.domain.usecase.GetOffers
import com.dracul.task.screens.main.MainScreenDirections
import dagger.Component
import dagger.Module
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@Component(modules = [MainModule::class], dependencies = [MainDependencies::class])
interface MainComponent {
    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: MainDependencies): Builder
        fun build(): MainComponent
    }

    fun inject(target: MainViewModel)
}

@Module
class MainModule

interface MainDependencies {
    fun getOffers(): GetOffers
}


class MainViewModel : ViewModel() {
    @Inject
    lateinit var getOffers: GetOffers
    val isBottomsheetVisible = MutableStateFlow(false)

    init {
        DaggerMainComponent.builder().dependencies(dependencies = DaggerInjector.appComponent).build().inject(this@MainViewModel)
    }

    fun getOffersList() = getOffers.execute().offers
    fun setAnywhere(edTo: EditText, place: String) {
        edTo.setText(place)
    }

    fun hideBottomsheet() {
        isBottomsheetVisible.value = false
    }

    fun showBottomsheet() {
        isBottomsheetVisible.value = true
    }

    fun setPlaceFromRecomendation(etTo: EditText, town: String) {
        etTo.setText(town)
    }

    fun navigateToPlug(navController: NavController) {
        navController.navigate(MainScreenDirections.actionPlug())
    }

    fun navigateToTicketsOption(navController: NavController, from: String, to: String) {
        navController.navigate(MainScreenDirections.actionTicketsOptions(from,to))
    }


}

