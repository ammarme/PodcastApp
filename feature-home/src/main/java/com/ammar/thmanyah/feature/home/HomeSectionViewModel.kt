package com.ammar.thmanyah.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ammar.thmanyah.core.data.di.IoDispatcher
import com.ammar.thmanyah.core.data.mapper.MapperSectionUiModel
import com.ammar.thmanyah.core.model.SectionUiModel
import com.ammar.thmanyah.feature.home.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeSectionViewModel
@Inject constructor(
    val getHomeSectionsUseCase: GetHomeSectionsUseCase,
    private val mapperSectionUiModel: MapperSectionUiModel,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    ViewModel() {
    private val _homeSectionState = mutableStateOf(HomeSectionState())
    val homeSectionState: State<HomeSectionState> get() = _homeSectionState

    fun getHomeSections() {
        val nextPageNumber = homeSectionState.value.currentPage + 1
        var isLastPageReached = false
        homeSectionState.value.totalPages?.let { totalPages ->
            isLastPageReached = nextPageNumber > totalPages
        }
        if (isLastPageReached.not()) {
            getHomeSectionsUseCase(nextPageNumber)
                .onStart {
                    _homeSectionState.value =
                        homeSectionState.value.copy(
                            isInitiated = true,
                            isShimmerEnabled = nextPageNumber == 1,
                            isPaginationLoading = nextPageNumber > 1,
                        )
                }
                .onEach {
                    val sections: ArrayList<SectionUiModel> =
                        homeSectionState.value.data ?: ArrayList()

                    val listOfUiSection = it.sections?.map { section ->
                        mapperSectionUiModel.map(section)
                    }
                    listOfUiSection?.let { it1 ->
                        sections.addAll(
                            it1
                        )
                    }

                    _homeSectionState.value =
                        homeSectionState.value.copy(
                            isShimmerEnabled = false,
                            isPaginationLoading = false,
                            data = sections,
                            totalPages = it.totalPages,
                            currentPage = nextPageNumber
                        )

                }.catch {
                    _homeSectionState.value =
                        homeSectionState.value.copy(
                            isShimmerEnabled = false,
                            isPaginationLoading = false,
                            error = it.message.orEmpty()
                        )
                }.flowOn(
                    ioDispatcher
                ).launchIn(viewModelScope)
        }
    }

    fun refreshHomeSections() {
        _homeSectionState.value = HomeSectionState()
        getHomeSections()
    }
}