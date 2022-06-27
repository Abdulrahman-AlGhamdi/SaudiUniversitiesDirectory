package com.ss.universitiesdirectory.repository.universities

import android.content.Context
import com.ss.universitiesdirectory.R
import com.ss.universitiesdirectory.data.model.univeristy.UniversityModel
import com.ss.universitiesdirectory.data.remote.ApiService
import com.ss.universitiesdirectory.data.remote.ResponseState
import com.ss.universitiesdirectory.data.remote.ResponseState.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject

class UniversitiesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService
) : UniversitiesRepository {

    private var _universitiesState = MutableStateFlow<ResponseState<List<UniversityModel>>>(Idle())
    override val universitiesState = _universitiesState.asStateFlow()

    override suspend fun getAllUniversities(region: String) {
        try {
            _universitiesState.value = Progress()
            val language = Locale.getDefault().language
            val response = apiService.getAllUniversities(language, region)

            if (!response.isSuccessful) _universitiesState.value = Error(response.message())
            else response.body()?.let { _universitiesState.value = Success(it) }

        } catch (exception: UnknownHostException) {
            _universitiesState.value = Error(context.getString(R.string.connection_message))
        } catch (exception: Exception) {
            exception.localizedMessage?.let { _universitiesState.value = Error(it) }
            exception.printStackTrace()
        }
    }
}