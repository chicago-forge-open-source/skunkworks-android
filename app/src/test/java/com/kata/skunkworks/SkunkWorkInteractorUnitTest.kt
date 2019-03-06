package com.kata.skunkworks

import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SkunkWorkInteractorUnitTest {

    private val list: List<SkunkWork> = listOf(
        "Can Beam",
        "Mini Drone Forge Tour Guide",
        "Smart Light That Goes Red When Build Fails",
        "NFC Chip That Gives Wifi Access",
        "NFC Ventra Clothing",
        "Train Set",
        "Nap Pods",
        "DX War Room",
        "Greeting Robot That Recognizes You Based On Key Card",
        "Cool Light For When Creative Collision is Ready",
        "Interactive Room Reservation System",
        "Custom Magnet All The Things",
        "Amiibo Features Around Features Around The Office",
        "Card Wall With NFC Chips"
    ).map(::SkunkWork)

    @Mock
    private lateinit var mockRepo: SkunkWorkRepository

    @Test
    fun findsAllSkunkwork() {
        whenever(mockRepo.findAllSkunkWorks()).thenReturn(list)
        val interactor = SkunkWorkInteractor(mockRepo)
        assertEquals(list, interactor.findAllSkunkWork())
    }
}