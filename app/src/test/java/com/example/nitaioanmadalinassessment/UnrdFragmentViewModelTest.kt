package com.example.nitaioanmadalinassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nitaioanmadalinassessment.data.api.ApiHelper
import com.example.nitaioanmadalinassessment.data.api.RetrofitBuilder
import com.example.nitaioanmadalinassessment.data.repository.UnrdRepository
import com.example.nitaioanmadalinassessment.data.utils.Resource
import com.example.nitaioanmadalinassessment.ui.unrditemsfragment.UnrdFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class UnrdFragmentViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val unrdRepository = UnrdRepository(ApiHelper(RetrofitBuilder.apiUnrdService))

    @Test
    fun `getUnrdItemsNow when we get a SUCCESS response we set the value for unrdItemsResponse with that response`() = mainCoroutineRule.runBlockingTest {
        val unrdViewModel = UnrdFragmentViewModel(unrdRepository, testDispatcher)
        unrdViewModel.getUnrdItemsNow()
        Assert.assertEquals(Resource.loading(data = null), unrdViewModel.unrdItemsResponse.value)
    }

    @ExperimentalCoroutinesApi
    private fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) {
        this.testDispatcher.runBlockingTest {
            block()
        }
    }

    @ExperimentalCoroutinesApi
    class MainCoroutineRule(
        val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    ) : TestWatcher() {

        override fun starting(description: Description?) {
            super.starting(description)
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            super.finished(description)
            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }
    }
}