package com.example.notes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.databinding.FragmentNoteItemBinding
import com.example.notes.domain.Note


class NoteItemFragment : Fragment() {


    private val viewModel by lazy {
        ViewModelProvider(this)[NoteItemViewModel::class.java]
    }

    private var _binding: FragmentNoteItemBinding? = null
    private val binding: FragmentNoteItemBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteItemBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseIntent()

        viewModel.shouldFinishFragment.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().finish()
            }
        }
    }

    private fun launchAddMode() {
        binding.btSave.setOnClickListener {
            val title = binding.etTitle.text.trim().toString()
            val description = binding.etDescription.text.trim().toString()
            viewModel.addNote(title, description)
        }

    }

    private fun launchEditMode(noteId: Int) {
        viewModel.getNote(noteId)
        viewModel.noteItem.observe(viewLifecycleOwner) {
            with(binding) {
                etTitle.setText(it.title)
                etDescription.setText(it.description)
            }
        }
        binding.btSave.setOnClickListener {
            val title = binding.etTitle.text.trim().toString()
            val description = binding.etDescription.text.trim().toString()
            viewModel.editNote(title, description)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseIntent() {
        val intent = requireActivity().intent
        val mode =
            intent.getStringExtra(EXTRA_MODE) ?: throw RuntimeException("Launch mode is null")
        when (mode) {
            ADD_MODE -> {
                launchAddMode()
            }

            EDIT_MODE -> {
                val id = intent.getIntExtra(EXTRA_NOTE_ID, Note.UNDEFINED_ID)
                launchEditMode(id)
            }

            else -> throw RuntimeException("Undefined mode: $mode")
        }
    }

    companion object {

        fun newInstance() = NoteItemFragment()

        const val EXTRA_MODE = "mode"
        const val ADD_MODE = "add"
        const val EDIT_MODE = "edit"
        const val EXTRA_NOTE_ID = "id"
    }
}