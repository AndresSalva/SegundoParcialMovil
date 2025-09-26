package com.andressalvatierra.programovil.features.profile.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileEmailTest {

    @Test
    fun `create ProfileEmail with valid email should succeed`() {
        // Arrange
        val validEmail = "test@example.com"

        // Act
        val profileEmail = ProfileEmail(validEmail)

        // Assert
        assertEquals(validEmail, profileEmail.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create ProfileEmail with invalid email format should throw exception`() {
        // Act
        ProfileEmail("invalid-email")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create ProfileEmail with empty email should throw exception`() {
        // Act
        ProfileEmail("")
    }
}