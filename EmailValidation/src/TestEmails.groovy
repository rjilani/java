/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

def file = new File("./emails.txt")

file.eachLine {

    //println it
    if (!(it.contains("[Valid email addresses]") || it.contains("[Invalid email addresses]"))) {
        println it + ":" + (EmailValidation.isValidEmailAddress(it) ? "valid" : "invalid")
    }
}
