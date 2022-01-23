package com.irishinterest.irishinterest

import com.irishinterest.irishinterest.webservice.CountByLetter

class FilterCountByLettersTest {

    @Test
    fun filterStartsWithAlpha() {
        val list: List<CountByLetter> = listOf(
            CountByLetter("?", 23),
            CountByLetter("*", 1),
            CountByLetter("A", 1256),
            CountByLetter("B", 3660)
        )
        val onlyAlpha = list.map { it.filter { letter -> letter.alpha.first().isLetter() } }
        assert(onlyAlpha.count(), 2)
    }
}