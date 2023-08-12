package com.ivand.LinkShortener.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "links")
data class Link(
    var text: String="",
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq", allocationSize = 1)
    var id: Long = 0
)

