package com.rosamerino.codechallenge.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rosamerino.codechallenge.data.model.RepoItems
import com.rosamerino.codechallenge.data.repository.MainRepository
import com.rosamerino.codechallenge.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery
    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    private val _repoList = MutableLiveData<Result<RepoItems?>>(Result.success(null))
    val repoList: LiveData<Result<RepoItems?>> = _repoList

    fun fetchRepos() {
        viewModelScope.launch {
            _searchQuery.value?.let { q ->
                mainRepository.getRepos(q).collect {
                    _repoList.value = it
                }
            }
        }
    }
}
