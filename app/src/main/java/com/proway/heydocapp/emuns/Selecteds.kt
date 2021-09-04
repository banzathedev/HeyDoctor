package com.proway.heydocapp.emuns

enum class Selecteds(val chosed: String, val type: Int) {
    BY_DOC("Por Medico", 1),
    BY_PATIENT("Por Paciente", 2),
    BY_ID("Por Id", 3)
}