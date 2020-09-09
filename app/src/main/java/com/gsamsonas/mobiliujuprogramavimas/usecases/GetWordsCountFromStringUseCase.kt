package com.gsamsonas.mobiliujuprogramavimas.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetWordsCountFromStringUseCase @Inject constructor() {

    suspend operator fun invoke(string: String): Int {
        return withContext(Dispatchers.Main) {
            string.split(" ".toRegex()).filter { it.isNotBlank() }.size
        }
    }
}