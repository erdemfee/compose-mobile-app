package com.erdum.financetracker

import com.erdum.financetracker.models.Category
import com.erdum.financetracker.models.Expense
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.create(schema = setOf(Expense::class, Category::class))
val db: Realm = Realm.open(config)