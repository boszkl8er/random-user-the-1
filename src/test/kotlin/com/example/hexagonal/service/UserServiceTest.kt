package com.example.hexagonal.service

import com.example.hexagonal.model.UserModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceTest (
    @MockK private val userService: UserService
) {
    @Test
    fun `should success when find all user`(){
        // Given
        val gender = "male"
        val title = "title"
        val firstName = "Kittipong"
        val lastName = "Tongsawang"
        val seed = "foobar"
        val userModel =  UserModel(
            id = 1,
            gender = gender,
            title = title,
            first = firstName,
            last = lastName,
            seed = seed
        )
        every { userService.getAllUser() } returns listOf(userModel)

        // When
        val users = userService.getAllUser()

        // Then
        verify(exactly = 1) { userService.getAllUser() }
        assertEquals(1, users.size)
        assertEquals(1, users[0].id)
        assertEquals(gender, users[0].gender)
        assertEquals(title, users[0].title)
        assertEquals(firstName, users[0].first)
        assertEquals(lastName, users[0].last)
        assertEquals(seed, users[0].seed)
    }

    @Test
    fun `should success when find all user by seed with correct parameter`(){
        // Given
        val gender = "male"
        val title = "title"
        val criteria = "foobar"
        val firstName = "Lisa"
        val lastName = "Blackpink"
        val seed = "foobar"
        val userModel =  UserModel(
            id = 1,
            gender = gender,
            title = title,
            first = firstName,
            last = lastName,
            seed = seed
        )
        every { userService.getAllUserBySeed(criteria) } returns listOf(userModel)

        // When
        val users = userService.getAllUserBySeed(criteria)

        // Then
        verify(exactly = 1) { userService.getAllUserBySeed(criteria) }
        assertEquals(1, users.size)
        assertEquals(1, users[0].id)
        assertEquals(gender, users[0].gender)
        assertEquals(title, users[0].title)
        assertEquals(firstName, users[0].first)
        assertEquals(lastName, users[0].last)
        assertEquals(seed, users[0].seed)
    }
}