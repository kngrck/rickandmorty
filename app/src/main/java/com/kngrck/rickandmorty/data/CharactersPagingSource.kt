package com.kngrck.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kngrck.rickandmorty.domain.mappers.CharacterMapper
import com.kngrck.rickandmorty.domain.model.Character
import com.kngrck.rickandmorty.interactors.GetCharactersUseCase

class CharactersPagingSource(
    private val getCharactersUseCase: GetCharactersUseCase
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber - 1

        val response = getCharactersUseCase.invoke(pageNumber)

        if (!response.isSuccessful) {
            return LoadResult.Error(Throwable("Can't retrieve data!"))
        }

        return LoadResult.Page(
            data = response.body()?.results!!.map { CharacterMapper.buildFrom(it) },
            prevKey = previousKey,
            nextKey = getPageIndexFromNext(response.body()?.info!!.next)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {

        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

}