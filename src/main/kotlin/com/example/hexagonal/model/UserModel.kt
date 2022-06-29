package com.example.hexagonal.model
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Column

@Entity
@Table(name = "tbl_user")
data class UserModel (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") var id: Int? = null,
    @Column(name = "gender") var gender: String,
    @Column(name = "title") var title: String,
    @Column(name = "first_name") var first: String,
    @Column(name = "last_name") var last: String,
    @Column(name = "seed") var seed: String
)