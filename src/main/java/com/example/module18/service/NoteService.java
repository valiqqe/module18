package com.example.module18.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.module18.entity.Note;
import com.example.module18.entity.dto.*;
import com.example.module18.repository.NoteRepository;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_CONTENT_LENGTH = 1000;

    private final NoteRepository noteRepository;


    public CreateNoteResponse create(CreateNoteRequest request) {
        Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);

        if (validationError.isPresent()) {
            return CreateNoteResponse.failed(validationError.get());
        }

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        Note createdNote = noteRepository.save(note);

        System.out.println("created");
        return CreateNoteResponse.success(createdNote.getId());
    }

    public GetNotesResponse getNotes() {
        List<Note> notes = noteRepository.findAll();

        return GetNotesResponse.success(notes);
    }

    public UpdateNoteResponse update(UpdateNoteRequest request) {
        Optional<Note> optionalNote = noteRepository.findById(request.getId());

        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());

        noteRepository.save(note);

        return UpdateNoteResponse.success(note);
    }

    public DeleteNoteResponse delete(long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.invalidNoteId);
        }

        Note note = optionalNote.get();

        noteRepository.delete(note);

        return DeleteNoteResponse.success();
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (Objects.isNull(request.getTitle()) || request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        if (Objects.isNull(request.getContent()) || request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(CreateNoteResponse.Error.invalidTitle);
        }

        return Optional.empty();
    }

    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (Objects.nonNull(request.getTitle()) && request.getTitle().length() > MAX_TITLE_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        if (Objects.nonNull(request.getContent()) && request.getContent().length() > MAX_CONTENT_LENGTH) {
            return Optional.of(UpdateNoteResponse.Error.invalidTitleLength);
        }

        return Optional.empty();
    }

}
