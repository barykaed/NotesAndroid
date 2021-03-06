package com.kishorebabu.android.notes

import android.os.Build
import com.kishorebabu.android.notes.usecase.FetchNoteByIdUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class FetchNoteByIdUseCaseTest : BaseUseCaseTest() {

  private val fetchNoteByIdUseCase: FetchNoteByIdUseCase by inject()

  @Test
  fun `can fetch note with valid note id`() {
    `add fake notes in database`()
    runBlocking {
      val note = fetchNoteByIdUseCase.perform(noteIds.first())
      assertEquals(note?.id, noteIds.first())
    }
  }

  @Test
  fun `cannot fetch note with invalid note id`() {
    `add fake notes in database`()
    runBlocking {
      val note = fetchNoteByIdUseCase.perform(0)
      assertNull(note)
    }
  }
}