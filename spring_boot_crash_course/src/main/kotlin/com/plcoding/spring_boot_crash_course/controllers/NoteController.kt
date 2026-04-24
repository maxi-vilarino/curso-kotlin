package com.plcoding.spring_boot_crash_course.controllers

import com.plcoding.spring_boot_crash_course.controllers.NoteController.NoteResponse
import com.plcoding.spring_boot_crash_course.database.model.Note
import com.plcoding.spring_boot_crash_course.database.repository.NoteRepository
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.Instant

// POST http://localhost:8085/notes
// GET http://localhost:8085/notes?ownerId=123

@RestController
@RequestMapping("/notes")
class NoteController(private val repository: NoteRepository) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val ownerId: String          // was missing entirely or misspelled
    )

    data class NoteResponse(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping
    fun save(@RequestBody @Valid body: NoteRequest): NoteResponse {  // added @RequestBody
        val note = repository.save(
            Note(
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId(body.ownerId)  // was body.owenerId
            )
        )
        return note.toResponse()
    }

    @GetMapping  // was @getMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String,
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }
}

private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt
    )
}