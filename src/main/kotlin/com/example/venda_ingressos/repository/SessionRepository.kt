package com.example.venda_ingressos.repository

import com.example.venda_ingressos.entities.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SessionRepository : JpaRepository<Session, UUID>