package com.karine.tvshow.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Movies() : RealmObject() {

    @PrimaryKey var id = 0
    var title: String = ""
//    var overview: String = ""
//    var date: String = ""
//    var like: MutableList<String> = mutableListOf()
//    var photo: String = ""


}