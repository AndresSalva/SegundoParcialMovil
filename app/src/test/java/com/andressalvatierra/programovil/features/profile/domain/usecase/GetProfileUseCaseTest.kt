package com.andressalvatierra.programovil.features.profile.domain.usecase

import com.andressalvatierra.programovil.features.profile.domain.model.*
import com.andressalvatierra.programovil.features.profile.domain.repository.IProfileRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetProfileUseCaseTest {

    private val mockRepository: IProfileRepository = mockk()
    private val getProfileUseCase = GetProfileUseCase(mockRepository)

    @Test
    fun `invoke should return success when repository returns success`() = runTest {
        // Arrange
        val successfulProfile = ProfileModel(
            name = ProfileName("Test Name"),
            email = ProfileEmail("test@test.com"),
            cellphone = ProfileCellphone("12345678"),
            pathUrl = ProfileUrl("https://example.com"),
            summary = ProfileSummary("A summary")
        )
        // Le decimos al mock que devuelva un resultado exitoso.
        every { mockRepository.fetchData() } returns Result.success(successfulProfile)

        // Act
        // Llamamos a la función suspendida 'invoke'. runTest maneja el delay(3000) por nosotros.
        val result = getProfileUseCase.invoke()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(successfulProfile, result.getOrNull())
    }

    @Test
    fun `invoke should return failure when repository returns failure`() = runTest {
        // Arrange
        val exception = Exception("Failed to fetch data from repository")
        // Le decimos al mock que devuelva un resultado fallido.
        every { mockRepository.fetchData() } returns Result.failure(exception)

        // Act
        val result = getProfileUseCase.invoke()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}