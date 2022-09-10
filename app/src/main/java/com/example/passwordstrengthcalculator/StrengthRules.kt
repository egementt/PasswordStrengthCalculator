package com.example.passwordstrengthcalculator

class StrengthRules() {

    fun anyLetterAndDigit(password: String): Boolean {
        return password.any { c: Char ->  c.isDigit() && c.isLetter() }
    }

    fun validLength(password: String): Boolean {
          return password.length >= 8
    }

    fun anyUppercase(password: String): Boolean {
        return password.any{ c-> c.isUpperCase()}
    }


}