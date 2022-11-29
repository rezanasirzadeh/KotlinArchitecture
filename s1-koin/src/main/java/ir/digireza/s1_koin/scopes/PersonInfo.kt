package ir.digireza.s1_koin.scopes

import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.dsl.scoped

class PersonInfo {
    fun showInfo(): String {
        return "reza Nasirzadeh"
    }
}

@KoinReflectAPI
val personModule = module {
    scope<ScopesActivity> {
        scoped<PersonInfo>()
    }
}