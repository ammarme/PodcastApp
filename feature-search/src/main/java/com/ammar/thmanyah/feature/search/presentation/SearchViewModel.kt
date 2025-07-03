package com.ammar.thmanyah.feature.search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ammar.thmanyah.core.data.di.IoDispatcher
import com.ammar.thmanyah.core.data.mapper.MapperSectionUiModel
import com.ammar.thmanyah.feature.search.domain.usecase.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


@HiltViewModel
class SearchViewModel
@Inject constructor(
    val getSearchResultUseCase: GetSearchResultUseCase,
    private val mapperSectionUiModel: MapperSectionUiModel,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    ViewModel() {
    private val _searchScreenState = mutableStateOf(SearchScreenState())
    val searchScreenState: State<SearchScreenState> get() = _searchScreenState

    @OptIn(FlowPreview::class)
    fun getSearchResult(searchKey: String) {

        getSearchResultUseCase(searchKey)
            .debounce(200)
            .conflate()
            .onStart {
                _searchScreenState.value =
                    searchScreenState.value.copy(
                        isLoading = true
                    )
            }
            .onEach {
                val listOfUiSection = it.sections?.map { section ->
                    mapperSectionUiModel.map(section)
                }
                _searchScreenState.value =
                    searchScreenState.value.copy(
                        isLoading = false,
                        data = listOfUiSection,
                    )

            }.catch {
                _searchScreenState.value =
                    searchScreenState.value.copy(
                        isLoading = false,
                        error = it.message.orEmpty()
                    )
            }.flowOn(
                ioDispatcher
            ).launchIn(viewModelScope)
    }

    fun refreshSearch(searchKey: String) {
        _searchScreenState.value = SearchScreenState()
        getSearchResult(searchKey)
    }

}